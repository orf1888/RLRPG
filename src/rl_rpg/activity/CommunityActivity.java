package rl_rpg.activity;

import rl_rpg.utils.Utils;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class CommunityActivity extends Activity
{

	TextView regPlayers, onlPlayers, onlFriends;
	Button friend, searchPlayer;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_community );

		// init buttons
		regPlayers= (TextView) findViewById( R.id.textCommRegPlay );
		onlPlayers= (TextView) findViewById( R.id.textCommOnlPlay );
		onlFriends= (TextView) findViewById( R.id.textCommOnlFrie );
		friend= (Button) findViewById( R.id.btnFriend );
		Context context= getApplicationContext();
		friend.setOnClickListener( new OnClickListener()
		{

			@Override
			public void onClick( View v )
			{
				Utils.startNewActivity( CommunityActivity.this, PlayerListViewActivity.class );
			}
		} );
		searchPlayer= (Button) findViewById( R.id.btnSearchPlay );
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		RLRPGApplication.performSave();
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		getMenuInflater().inflate( R.menu.community, menu );
		return true;
	}

}
