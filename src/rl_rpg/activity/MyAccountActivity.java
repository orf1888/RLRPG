package rl_rpg.activity;

import rl_rpg.activity.adapter.SkillsListAdapter;
import rl_rpg.model.Profil;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;


public class MyAccountActivity extends Activity
{
	ProfilWidget ekranProfil;

	ListView list;


	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		setContentView( R.layout.activity_my_account );

		ekranProfil= new ProfilWidget( this, false );
		ekranProfil.nick= (TextView) findViewById( R.id.textMyAccNick );
		ekranProfil.lvl= (TextView) findViewById( R.id.textMyAccLvl );
		ekranProfil.xp= (TextView) findViewById( R.id.textMyAccXp );
		ekranProfil.updateProfil();

		/* Skill list init */

		list= (ListView) findViewById( R.id.skillsList );
		list.setAdapter( new SkillsListAdapter( this, Profil.getLocal().getSkills(), getResources(),true ) );
	}


	@Override
	protected void onPause()
	{
		super.onPause();
		ekranProfil.dropListeners();

		RLRPGApplication.performSave();
	}


	@Override
	protected void onResume()
	{
		super.onResume();
		ekranProfil.setListeners();
		ekranProfil.updateProfil();
	}


	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.my_account, menu );
		return true;
	}
}
