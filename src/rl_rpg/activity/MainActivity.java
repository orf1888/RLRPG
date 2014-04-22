package rl_rpg.activity;

import java.io.IOException;

import rl_rpg.activity.R;
import rl_rpg.model.Profil;
import rl_rpg.model.Profil.OnChangeProfilListener;
import rl_rpg.utils.L;


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
	// widok
	static class EkranGlowny
	{
		public TextView nick, lvl, xp;
		public Button challenge, community;
		public ImageButton myAccount;

		Activity parent;

		EkranGlowny( Activity parent )
		{
			this.parent = parent;
		}
		
		// funkcja wykonywana automatycznie dla kazdej zmiany zachodzacej w profil
		public void updateProfil()
		{
			nick.setText( "NICK: " + Profil.getLocal().getNick() );
			lvl.setText( "LVL: " + Profil.getLocal().getLvl() );
			xp.setText( "XP: " + Profil.getLocal().getXp() );
		}

		public void setListeners( final Context context )
		{
			
			/////////////////////////
			//// onChange
			Profil.getLocal().addOnChangeListener( new OnChangeProfilListener()
			{
				@Override
				public void onChange()
				{
					parent.runOnUiThread( new Runnable()
					{
						@Override
						public void run()
						{
							updateProfil();
						}
					} );
				}
			} );

			/////////////////////////
			//// onClick
			challenge.setOnClickListener( new OnClickListener()
			{
				@Override
				public void onClick( View v )
				{
					/* Dzia³a? */
					Intent intent = new Intent( context, ChallengeListViewActivity.class );
					intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
					context.startActivity( intent );
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
					Intent intent = new Intent( context, MyAccountActivity.class );
					intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
					context.startActivity( intent );
				}
			} );
		}
	}

	static EkranGlowny ekranGlowny;


	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		
		RLRPGApplication.registerActivity( this );

		/* init */
		ekranGlowny = new EkranGlowny(this);

		/* init view */
		setContentView( R.layout.activity_main );
		ekranGlowny.nick = (TextView) findViewById( R.id.textLblNick );
		ekranGlowny.lvl = (TextView) findViewById( R.id.textLblLvl );
		ekranGlowny.xp = (TextView) findViewById( R.id.textLblXp );
		ekranGlowny.updateProfil();

		ekranGlowny.challenge = (Button) findViewById( R.id.btnChallenge );
		ekranGlowny.community = (Button) findViewById( R.id.btnCom );
		ekranGlowny.myAccount = (ImageButton) findViewById( R.id.myAcc );

		ekranGlowny.setListeners( getApplicationContext() );
	}
	
	
	/**
	 * wczytujemy zapis stanu
	 */
	@Override
	protected void onPause() {
		super.onPause();
		try {
			RLRPGApplication.performSave();
		} catch ( IOException e ) {
			L.log( "Error MainActivity "+e.getMessage() );
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		try {
			RLRPGApplication.performLoad();
		} catch ( Exception e ) {
			L.log( "Error MainActivity "+e.getMessage() );
			e.printStackTrace();
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.main, menu );
		return true;
	}


}