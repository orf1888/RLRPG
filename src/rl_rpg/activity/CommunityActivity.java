package rl_rpg.activity;

import rl_rpg.activity.animation.AnimationThing;
import rl_rpg.utils.L;
import rl_rpg.utils.Utils;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class CommunityActivity extends Activity
{

	AnimationThing a1, a2, a3;

	TextView regPlayers, onlPlayers, onlFriends;
	Button friend, searchPlayer;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_community );

		if( savedInstanceState != null && savedInstanceState.getBoolean( "saved", false ) == true ) {
			// restore save
			//int size= savedInstanceState.getInt( "player_count", 0 );
		} else {
			// create default

			/*1. zrobic aplikacje
			   -aplikacja pozwala pobrac osobe
			   -aplikacja ma przyciski do zarzadzania osobami, online osobami itd
			  2. 
			 */
		}

		//1. kolejkujemy ¿¹danie polaczenia z serwerem i akcje do wykonania
		//2. jest mozliwosc anulowania ¿¹dania! np usuniecia z listy
		//3. jesli ¿¹danie sie wykona, zostaje wykonany callback [tu musimy uwzglednic .runOnUiThread]
		//4. na czas czekania na ¿¹danie, zrobic animacje
		// -animacja polega na tym, ze co sekunde przeskakuj¹ kropki w miejscu liczb: "." ".." "..." "."
		// -ogarnac animacje
		//5. callback ustawia liczby i wy³¹cza animacje
		//6. w przypadku negatywnego wykonania siê ¿¹dania, mo¿na ew. ponowiæ ¿¹danie za jakiœ czas

		//init buttons
		regPlayers= (TextView) findViewById( R.id.textCommRegPlay );
		onlPlayers= (TextView) findViewById( R.id.textCommOnlPlay );
		onlFriends= (TextView) findViewById( R.id.textCommOnlFrie );
		friend= (Button) findViewById( R.id.btnFriend );

		AnimationThing.OnTickListener onTick= new AnimationThing.OnTickListener()
		{
			@Override
			public void onTick( Object data, int tick )
			{
				// tick = 100ms, zmieniamy co sekunde czyli co 10 tickow
				if( tick % 10 != 0 )
					return;
				TextView t= (TextView) data;
				String txt= t.getText().toString();
				if( txt.length() > 3 )
					t.setText( "." );
				else
					t.setText( txt + "." );
			}
		};
		a1= new AnimationThing( "regPlayers", regPlayers, this, onTick );
		a2= new AnimationThing( "onlPlayers", onlPlayers, this, onTick );
		a3= new AnimationThing( "onlFriends", onlFriends, this, onTick );

		clearData();

		friend.setOnClickListener( new OnClickListener()
		{
			@Override
			public void onClick( View v )
			{
				Utils.startNewActivity( CommunityActivity.this, PlayerListViewActivity.class, null );
			}
		} );
		searchPlayer= (Button) findViewById( R.id.btnSearchPlay );
	}


	private void clearData()
	{
		regPlayers.setText( "." );
		onlPlayers.setText( "." );
		onlFriends.setText( "." );
	}

	@Override
	protected void onSaveInstanceState( Bundle savedInstanceState )
	{
		super.onSaveInstanceState( savedInstanceState );
		try {
			savedInstanceState.putBoolean( "saved", true );
		} catch ( Exception e ) {
			L.logError( e );
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		a1.start();
		a2.start();
		a3.start();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		a1.stop();
		a2.stop();
		a3.stop();
		RLRPGApplication.performSave();
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		getMenuInflater().inflate( R.menu.community, menu );
		return true;
	}

}
