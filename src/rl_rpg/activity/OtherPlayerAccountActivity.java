package rl_rpg.activity;

import rl_rpg.activity.adapter.SkillsListAdapter;
import rl_rpg.model.Profil;
import rl_rpg.utils.Utils;
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
		player= Profil.Manager.getProfilById( getIntent().getExtras().getInt( "ProfilID" ) );
		// init Views
		setTitle( "Player: " + player.getNick() + " LVL: " + player.getLvl() );
		playerSkillsList= (ListView) findViewById( R.id.skillsList );
		playerSkillsList.setAdapter( new SkillsListAdapter( this, player.getSkills(), getResources(), false ) );
		performedChallenge= (Button) findViewById( R.id.btnPlayerPerformedChallange );
		progress= (Button) findViewById( R.id.btnPlayerProgress );
		msg= (Button) findViewById( R.id.btnPlayerSendMsg );
		playerDetails= (Button) findViewById( R.id.btnPlayerShowDetails );
		// set button width
		Utils.setButtonWidth( performedChallenge, OtherPlayerAccountActivity.this );
		Utils.setButtonWidth( progress, OtherPlayerAccountActivity.this );
		Utils.setButtonWidth( msg, OtherPlayerAccountActivity.this );
		Utils.setButtonWidth( playerDetails, OtherPlayerAccountActivity.this );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.other_player_account, menu );
		return true;
	}

}
