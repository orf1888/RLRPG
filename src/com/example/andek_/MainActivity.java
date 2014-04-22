package com.example.andek_;

import com.example.andek_.Profil.OnChangeProfilListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity
{
	// model
	static Profil profil;


	// widok
	static class EkranGlowny
	{
		public TextView nick, lvl, xp;
		public Button achivments, community;
		public ImageButton myAccount;

		// funkcja wykonywana automatycznie dla kazdej zmiany zachodzacej w profil
		public void updateProfil()
		{
			nick.setText( getNickLabel() + profil.getNick() );
			lvl.setText( getLvlLabel() + profil.getLvl() );
			xp.setText( getXpLabel() + profil.getXp() );
		}

		private String getNickLabel()
		{
			return "NICK: ";
		}

		private String getLvlLabel()
		{
			return "LVL: ";
		}

		private String getXpLabel()
		{
			return "XP: ";
		}

		public void setListeners( final Context context )
		{
			/////////////////////////
			//// onChange
			profil.addOnChangeListener( new OnChangeProfilListener()
			{
				@Override
				public void onChange()
				{
					updateProfil();
				}
			});
			
			/////////////////////////
			//// onClick
			achivments.setOnClickListener( new OnClickListener()
			{
				@Override
				public void onClick( View v )
				{
					/*Dzia³a?*/
					Intent intent = new Intent(context, AchivListViewActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent);
				}
			} );
			community.setOnClickListener( new OnClickListener()
			{
				@Override
				public void onClick( View v )
				{
					Toast.makeText( context, "Klikn¹³eœ community!", Toast.LENGTH_SHORT ).show();
				}
			} );
			myAccount.setOnClickListener( new OnClickListener()
			{
				@Override
				public void onClick( View v )
				{
					Toast.makeText( context, "Klikn¹³eœ na swoje konto!", Toast.LENGTH_SHORT ).show();
				}
			} );
		}
	}

	static EkranGlowny ekranGlowny;


	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		/* init */
		profil = Profil.Manager.load();
		ekranGlowny = new EkranGlowny();
		
		/* init view */
		setContentView( R.layout.activity_main );
		ekranGlowny.nick = (TextView) findViewById( R.id.textLblNick );
		ekranGlowny.lvl = (TextView) findViewById( R.id.textLblLvl );
		ekranGlowny.xp = (TextView) findViewById( R.id.textLblXp );
		ekranGlowny.updateProfil();

		ekranGlowny.achivments = (Button) findViewById( R.id.btnAchive );
		ekranGlowny.community = (Button) findViewById( R.id.btnCom );
		ekranGlowny.myAccount = (ImageButton) findViewById( R.id.myAcc );

		ekranGlowny.setListeners( getApplicationContext() );
		
		/* background */
		Thread mainThread = new Thread(new MainThread(this)); //new Thread( new MainThread() );
		mainThread.start();
	}


	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.main, menu );
		return true;
	}
	
	
	
	static public class MainThread extends Thread
	{
		static long diff = 2000;
		
		long lastTime;
		
		Activity parent;
		
		MainThread( Activity parent ){
			this.parent= parent;
		}
		
		void upXp(){
			parent.runOnUiThread( new Runnable()
			{
				@Override
				public void run()
				{
					profil.setXp( profil.getXp() + 1 );
					ekranGlowny.updateProfil();
				}
			} );
		}
		
		@Override
		public void run()
		{
			lastTime= System.currentTimeMillis();
			
			try {
				while(true){
					long time= System.currentTimeMillis();
					
					//
					if( time-lastTime > diff){
						upXp();
						lastTime= time;
					}
					//
					
					Thread.sleep(100);
				}
			} catch ( Exception e ) {
				e.printStackTrace();
			}
		}
	}

}