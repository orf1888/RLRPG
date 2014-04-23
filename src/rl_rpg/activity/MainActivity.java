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

	static class EkranGlowny
	{
		public Button challenge, community;

		Activity parent;

		EkranGlowny( Activity parent )
		{
			this.parent = parent;
		}


		public void setListeners()
		{
			final Context context = parent.getApplicationContext();
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
		}
		
		public void dropListeners()
		{
		}
	}

	static EkranGlowny ekranGlowny;
	static ProfilWidget ekranProfil;


	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		
		try {
			RLRPGApplication.performLoad();
		} catch ( Exception e ) {
			L.logError( e );
		}

		RLRPGApplication.registerActivity( this );

		/* init */
		ekranGlowny = new EkranGlowny( this );
		ekranProfil = new ProfilWidget( this, true );

		/* init view */
		setContentView( R.layout.activity_main );
		ekranProfil.nick = (TextView) findViewById( R.id.textLblNick );
		ekranProfil.lvl = (TextView) findViewById( R.id.textLblLvl );
		ekranProfil.xp = (TextView) findViewById( R.id.textLblXp );
		ekranProfil.myAccount = (ImageButton) findViewById( R.id.myAcc );
		ekranProfil.updateProfil();

		ekranGlowny.challenge = (Button) findViewById( R.id.btnChallenge );
		ekranGlowny.community = (Button) findViewById( R.id.btnCom );
	}


	@Override
	protected void onPause()
	{
		super.onPause();
		try {
			ekranGlowny.dropListeners();
			ekranProfil.dropListeners();
			RLRPGApplication.performSave();
		} catch ( IOException e ) {
			L.logError( e );
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		ekranGlowny.setListeners();
		ekranProfil.setListeners();
		ekranProfil.updateProfil();
	}


	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		try {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate( R.menu.main, menu );
			return true;
		} catch ( Exception e ) {
			L.logError( e );
			return false;
		}
	}


}