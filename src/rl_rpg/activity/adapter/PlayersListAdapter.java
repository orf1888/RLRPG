package rl_rpg.activity.adapter;

import java.util.List;

import rl_rpg.activity.R;
import rl_rpg.model.Player;
import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class PlayersListAdapter extends ListAdapter
{

	@SuppressWarnings("rawtypes")
	public PlayersListAdapter( Activity a, List d, Resources res )
	{
		super( a, d, res, R.layout.player_item );
	}

	public static class ViewHolder implements IViewHolder
	{
		ViewHolder( View viev, ListAdapter parent )
		{
			playerName= (TextView) viev.findViewById( R.id.textPlayerNick );
			showAcc= (Button) viev.findViewById( R.id.btnShowPlayAcc );
			this.parent= parent;
		}

		public TextView playerName;
		public Button showAcc;
		ListAdapter parent;

		@Override
		public void clear()
		{
			playerName.setText( "No Player" );
		}

		@Override
		public void set( Object model, int position )
		{
			Player tempValues= (Player) model;
			playerName.setText( tempValues.getNick() );
			showAcc.setTag( position );
			showAcc.setOnClickListener( new OnClickListener()
			{

				@Override
				public void onClick( View v )
				{
					Integer position= (Integer) v.getTag();
					Player play= (Player) parent.data.get( position );
					Toast.makeText( parent.inflater.getContext(), "Klikn��e� " + play.getNick(), Toast.LENGTH_SHORT ).show();
				}
			} );
		}
	}



	@Override
	OnClickListener getOnRowClickListener()
	{
		return null;
	}

	@Override
	IViewHolder createViewHolder( View vi )
	{
		return new ViewHolder( vi, this );
	}

}
