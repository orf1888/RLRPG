package rl_rpg.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import rl_rpg.activity.animation.AnimationThing;
import rl_rpg.model.Profil;
import rl_rpg.utils.L;
import rl_rpg.utils.MapWithDefaults;
import android.app.Application;
import android.os.Environment;


public class RLRPGApplication extends Application
{
	private static int version= VersionToInt( 1, 1, 1 );
	Thread mainThread= null;
	boolean initialized= false;

	private static final Map<String, SaveLoadListener> saveLoadListeners= new HashMap<String, SaveLoadListener>();
	private static final Map<String, LoopListener> loopListeners= new HashMap<String, LoopListener>();

	//
	// public methods
	//

	@Override
	public void onCreate()
	{
		try {
			initApplication();
		} catch ( Exception e ) {
			L.logError( e );
		}
	}


	private static int VersionToInt( int i, int j, int k )
	{
		int x= 0; // future zastosowanie, np. mozna tu upchac litere
		L._assert( i >= 0 && j >= 0 && k >= 0 && x >= 0 && i < 100 && j < 100 && k < 1000 && x < 100 );
		return i * 10000000 + j * 100000 + k * 100 + x;
	}


	public static void addSaveLoadListener( SaveLoadListener listener )
	{
		saveLoadListeners.put( listener.getNamePrefix(), listener );
	}


	public static void addLoopListener( LoopListener listener )
	{
		loopListeners.put( listener.getNamePrefix(), listener );
	}


	@SuppressWarnings("rawtypes")
	public static void performSave()
	{
		ObjectOutputStream s= null;
		try {
			Map<String, Map> maps= new HashMap<String, Map>();
			for( SaveLoadListener listener : saveLoadListeners.values() )
				maps.put( listener.getNamePrefix(), listener.onSave() );

			FileOutputStream stream= new FileOutputStream( getSaveFile() );
			s= new ObjectOutputStream( stream );
			s.writeInt( version );
			s.writeInt( maps.hashCode() );
			s.writeObject( maps );
		} catch ( IOException e ) {
			L.logError( e );
		} finally {
			try {
				s.close();
			} catch ( IOException e ) {
			}
		}
	}


	@SuppressWarnings("unchecked")
	private static boolean performLoad()
	{
		File file= getSaveFile();
		if( file == null || !file.isFile() ) {
			L.log( "performLoad no file" );
			return false;
		}

		ObjectInputStream s= null;
		try {
			FileInputStream stream= new FileInputStream( file );
			s= new ObjectInputStream( stream );

			@SuppressWarnings("unused")
			int save_version= (int) s.readInt();

			int hash= (int) s.readInt();
			HashMap<String, Object> maps= (HashMap<String, Object>) s.readObject();
			if( hash != maps.hashCode() )
				throw new Exception( "corrupted save" );

			L.log( "performLoad " + maps.keySet().size() );

			for( String key : maps.keySet() ) {
				SaveLoadListener listener= saveLoadListeners.get( key );
				if( listener == null )
					continue;
				listener.onLoad( new MapWithDefaults( maps.get( key ) ) );
			}

			return true;

		} catch ( Exception e ) {
			L.logError( e );
			return false;
		} finally {
			try {
				s.close();
			} catch ( Exception e ) {
			}
		}
	}

	private static void performLoadDefaults()
	{
		for( SaveLoadListener listener : saveLoadListeners.values() ) {
			if( listener == null )
				continue;
			listener.onLoad( new MapWithDefaults( null ) );
		}
	}

	//
	// private methods
	//

	private void initApplication()
	{
		L.log( "initApplication" );

		Profil.Manager.instance().init();
		AnimationThing.Manager.instance().init();

		boolean loaded= RLRPGApplication.performLoad();
		if( !loaded )
			RLRPGApplication.performLoadDefaults();

		/* background */
		if( mainThread == null ) {
			mainThread= new Thread( new MainThread() ); //new Thread( new MainThread() );
			L.log( "new mainThread" );
			mainThread.start();
		}
	}


	private static File getSaveFile()
	{
		// Context.getExternalFilesDir
		File root= Environment.getExternalStorageDirectory();
		File dir= new File( root, "rl_rpg" );
		if( !dir.exists() )
			dir.mkdir();
		return new File( dir, "save" );
	}

	static public interface SaveLoadListener
	{
		Map<String, Object> onSave();

		void onLoad( MapWithDefaults map );

		String getNamePrefix();
	}

	static public interface LoopListener
	{
		void onTick();

		String getNamePrefix();
	}


	static class MainThread extends Thread
	{
		static long diff= 2000;

		long lastTime;

		@Override
		public void run()
		{
			lastTime= System.currentTimeMillis();

			try {
				while ( true ) {
					long time= System.currentTimeMillis();

					//
					if( time - lastTime > diff ) {
						Profil.getLocal().addXp( 1 );
						if( Profil.getLocal().getSkills().get( 0 ) != null )
							Profil.getLocal().getSkills().get( 0 ).addValue( 4 );
						lastTime= time;
					}
					//


					// 
					//Map<String, Map> maps= new HashMap<String, Map>();
					for( LoopListener listener : loopListeners.values() )
						listener.onTick();
					//Profil.Manager.update();
					//


					Thread.sleep( 100 );
				}
			} catch ( Exception e ) {
				L.logError( e );
			}
		}
	}
}
