package rl_rpg.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rl_rpg.model.Profil;
import rl_rpg.utils.L;
import android.app.Activity;
import android.app.Application;
import android.os.Environment;
import android.provider.SyncStateContract.Constants;


public class RLRPGApplication extends Application
{
	Thread mainThread = null;
	boolean initialized = false;
	static Map<String, SaveLoadListener> saveLoadListeners;

	//
	// public methods
	//

	public static void registerActivity( Activity activity )
	{
		((RLRPGApplication) activity.getApplication())._registerActivity( activity );
	}


	public static void addSaveLoadListener( SaveLoadListener listener )
	{
		if( saveLoadListeners == null )
			saveLoadListeners = new HashMap<String, SaveLoadListener>();
		saveLoadListeners.put( listener.getNamePrefix(), listener );
	}


	private static File getSaveFile()
	{
		// Context.getExternalFilesDir
		File root = Environment.getExternalStorageDirectory();
		File dir = new File( root, "rl_rpg" );
		if( !dir.exists() )
			dir.mkdir();
		return new File( dir, "save" );
	}

	public static void performSave() throws IOException
	{
		Map<String, Map> maps = new HashMap<String, Map>();
		for (SaveLoadListener listener : saveLoadListeners.values())
			maps.put( listener.getNamePrefix(), listener.onSave() );

		FileOutputStream stream = new FileOutputStream( getSaveFile() );
		ObjectOutputStream s = new ObjectOutputStream( stream );
		s.writeObject( maps );
		s.close();
	}


	public static void performLoad() throws IOException, ClassNotFoundException
	{
		L.log( "performLoad" );
		File file = getSaveFile();
		if( file == null ) {
			L.log( "performLoad no file" );
			return;
		}
		FileInputStream stream = new FileInputStream( file );
		ObjectInputStream s = new ObjectInputStream( stream );
		HashMap<String, Object> maps = (HashMap<String, Object>) s.readObject();
		s.close();

		for (String key : maps.keySet()) {
			SaveLoadListener listener = saveLoadListeners.get( key );
			if( listener == null )
				continue;
			listener.onLoad( (Map) maps.get( key ) );
		}
	}


	//
	// private methods
	//

	private void _registerActivity( Activity activity )
	{
		if( !initialized )
			initApplication();
	}


	private void initApplication()
	{
		L.log( "initApplication" );

		initialized = true;

		/* background */
		if( mainThread == null ) {
			mainThread = new Thread( new MainThread() ); //new Thread( new MainThread() );
			mainThread.start();
		}
	}


	static public interface SaveLoadListener
	{
		Map<String, Object> onSave();

		void onLoad( Map<String, Object> map );

		String getNamePrefix();
	}


	static public class MainThread extends Thread
	{
		static long diff = 2000;

		long lastTime;

		void upXp()
		{
			Profil.getLocal().setXp( Profil.getLocal().getXp() + 1 );
		}

		@Override
		public void run()
		{
			lastTime = System.currentTimeMillis();

			try {
				while ( true ) {
					long time = System.currentTimeMillis();

					//
					if( time - lastTime > diff ) {
						upXp();
						lastTime = time;
					}
					//


					// 
					Profil.Manager.update();
					//


					Thread.sleep( 100 );
				}
			} catch ( Exception e ) {
				e.printStackTrace();
			}
		}
	}
}
