package rl_rpg.activity;

import java.util.ArrayList;

import rl_rpg.activity.adapter.PlayersListAdapter;
import rl_rpg.model.Player;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;


public class PlayerListViewActivity extends Activity
{

	ListView list;
	PlayersListAdapter adapter;
	public PlayerListViewActivity PlayerListView= null;
	public ArrayList<Player> playersArr= new ArrayList<Player>();

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.players_list );

		PlayerListView= this;
		setListData();
		Resources res= getResources();
		list= (ListView) findViewById( R.id.playersList ); // List defined in XML
															// ( See
		adapter= new PlayersListAdapter( PlayerListView, playersArr, res );
		list.setAdapter( adapter );
	}

	public void setListData()
	{

		for( int i= 0; i < 11; i++ ) {

			final Player player= new Player();
			player.setNick( "Player " + i );
			playersArr.add( player );
		}

	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.player_list_view, menu );
		return true;
	}

}
