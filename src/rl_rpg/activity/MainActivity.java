package rl_rpg.activity;

import rl_rpg.utils.Utils;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends Activity
{
	static class EkranGlowny
	{
		public Button challenge, community;

		Activity parent;

		EkranGlowny( Activity parent )
		{
			this.parent= parent;
		}

		/**
		 * ustawia ewentualne listenery. Uwaga, jesli cos jest wywolane poza w¹tkiem UI, musi byc uzyte 
		 * activity.runOnUiThread( new Runnable()
		 * Uwaga, jesli jakis listener odwo³uje siê poza UI (np. do modelu), trzeba go wywaliæ w funkcji dropListeners
		 */
		public void setListeners()
		{
			final Context context= parent.getApplicationContext();
			/////////////////////////
			//// onClick
			challenge.setOnClickListener( new View.OnClickListener()
			{
				@Override
				public void onClick( View v )
				{
					/* Dzia³a? */
					Utils.startNewActivity( context, ChallengeListViewActivity.class );
				}
			} );
			community.setOnClickListener( new View.OnClickListener()
			{
				@Override
				public void onClick( View v )
				{
					Utils.startNewActivity( context, CommunityActivity.class );
					//Toast.makeText( context, "Klikn¹³eœ community!", Toast.LENGTH_SHORT ).show();
				}
			} );
		}

		/**
		 * tutaj usuwamy listenery, jesli dodaliœmy jakieœ do modelu
		 */
		public void dropListeners()
		{
		}
	}

	private EkranGlowny ekranGlowny;
	private ProfilWidget ekranProfil;


	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		setContentView( R.layout.activity_main );

		ekranProfil= new ProfilWidget( this, true );
		ekranProfil.nick= (TextView) findViewById( R.id.textLblNick );
		ekranProfil.lvl= (TextView) findViewById( R.id.textLblLvl );
		ekranProfil.xp= (TextView) findViewById( R.id.textLblXp );
		ekranProfil.myAccount= (ImageButton) findViewById( R.id.myAcc );
		ekranProfil.updateProfil();

		ekranGlowny= new EkranGlowny( this );
		ekranGlowny.challenge= (Button) findViewById( R.id.btnChallenge );
		ekranGlowny.community= (Button) findViewById( R.id.btnCom );
	}


	@Override
	protected void onPause()
	{
		super.onPause();
		ekranGlowny.dropListeners();
		ekranProfil.dropListeners();

		RLRPGApplication.performSave();
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.main, menu );
		return true;
	}

	public void onBackPressed()
	{

		OnClickListener exitListener= new OnClickListener()
		{

			@Override
			public void onClick( DialogInterface dialog, int which )
			{
				L.log( "Finish app!" );
				finish();
			}
		};
		DialogBuilder exitDialog= new DialogBuilder( MainActivity.this, "Exit app", "Do you realy want to exit?",
				exitListener, DialogBuilder.createDefaultReturnListener(), "Yes", "No" );
		exitDialog.showDialog();
	}
}
