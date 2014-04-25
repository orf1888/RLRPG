package rl_rpg.activity;

import java.util.ArrayList;

import rl_rpg.activity.adapter.PlayersListAdapter;
import rl_rpg.model.Profil;
import rl_rpg.utils.L;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;


public class PlayerListViewActivity extends Activity
{
	ListView list;
	PlayersListAdapter adapter;
	public ArrayList<Profil> playersArr= new ArrayList<Profil>();

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.players_list );

		if( savedInstanceState != null && savedInstanceState.getBoolean( "saved", false ) == true ) {
			// restore save
			int size= savedInstanceState.getInt( "player_count", 0 );
			for( int i= 0; i < size; i++ ) {
				Profil p= (Profil) savedInstanceState.getSerializable( "player_" + i );
				playersArr.add( p );
			}
		} else {
			// create default
			setListData();
		}

		Resources res= getResources();
		list= (ListView) findViewById( R.id.playersList );

		adapter= new PlayersListAdapter( this, playersArr, res );
		list.setAdapter( adapter );
	}


	@Override
	protected void onSaveInstanceState( Bundle savedInstanceState )
	{
		super.onSaveInstanceState( savedInstanceState );
		try{
			savedInstanceState.putBoolean( "saved", true );
			savedInstanceState.putInt( "player_count", playersArr.size() );
			for( int i= 0; i < playersArr.size(); i++ )
				savedInstanceState.putSerializable( "player_" + i, playersArr.get( i ) );
		}catch (Exception e) {
			L.logError( e );
		}
	}

	public void setListData()
	{
		for( int i= 0; i < 11; i++ ) {
			int id= (int) (10 * Math.random() + 1);
			playersArr.add( Profil.Manager.getProfilById( id ) );
		}
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.player_list_view, menu );
		return true;
	}

}
