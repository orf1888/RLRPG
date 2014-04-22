package rl_rpg.activity;

import rl_rpg.model.Profil;
import android.app.Activity;
import android.app.Application;


public class RLRPGApplication extends Application
{

	Thread mainThread = null;
	
	
	public static void registerActivity( Activity activity )
	{
		((RLRPGApplication) activity.getApplication())._registerActivity( activity );
	}

	private void _registerActivity( Activity activity )
	{
		// aktualnie nic nie robimy z activity.
		
		/* background */
		if( mainThread==null ){
			mainThread = new Thread( new MainThread() ); //new Thread( new MainThread() );
			mainThread.start();
		}
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
