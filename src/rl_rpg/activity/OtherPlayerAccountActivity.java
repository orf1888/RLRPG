package rl_rpg.activity;

import rl_rpg.activity.adapter.SkillsListAdapter;
import rl_rpg.model.Profil;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;


public class OtherPlayerAccountActivity extends Activity
{

	Profil player;

	ListView playerSkillsList;

	Button performedChallenge, progress, msg, playerDetails;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_other_player_account );
		// init Player
		player=Profil.Manager.getProfilById( getIntent().getExtras().getInt( "ProfilID" ) );
		// init Views
		setTitle( "Player: "+player.getNick()+ " LVL: "+player.getLvl() );
		playerSkillsList= (ListView) findViewById( R.id.skillsList );
		playerSkillsList.setAdapter( new SkillsListAdapter( this, Profil.getLocal().getSkills(), getResources(), false ) );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.other_player_account, menu );
		return true;
	}

}
