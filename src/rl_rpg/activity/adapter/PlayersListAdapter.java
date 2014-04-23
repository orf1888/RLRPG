package rl_rpg.activity.adapter;

import java.util.ArrayList;

import rl_rpg.activity.R;
import rl_rpg.model.ChallengeListModel;
import rl_rpg.model.Player;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayersListAdapter extends BaseAdapter implements OnClickListener {

	private Activity activity;
	private ArrayList<?> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	int i = 0;

	public PlayersListAdapter(Activity activity, ArrayList<?> data,
			Resources res) {
		this.activity = activity;
		this.data = data;
		this.res = res;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		if (data.size() <= 0)
			return 1;
		else
			return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}
	
	public static class ViewHolder
	{
		ViewHolder( View viev )
		{
			playerName = (TextView) viev.findViewById( R.id.textPlayerNick );
			showAcc=(Button) viev.findViewById(R.id.btnShowPlayAcc);
		}

		public TextView playerName;
		public Button showAcc;
		//public TextView playerLvl;
		//public TextView playerXP;
		//etc...
	}
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		ViewHolder holder;

		if (convertView == null) {
			vi = inflater.inflate(R.layout.player_item, null);
			holder = new ViewHolder(vi);
			vi.setTag(holder);
		} else {
			holder = (ViewHolder) vi.getTag();
		}

		if (data.size() <= 0) {
			holder.playerName.setText("No Players!");

		} else {

			Player tempValues = (Player) data.get(position);
			/* Set data on screen */
			holder.playerName.setText(tempValues.getNick());
			holder.showAcc.setTag(position);
			holder.showAcc.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// Startowaæ nowe Activity
					Integer position = (Integer) v.getTag();
					Player play = (Player) data.get( position );
					
					Toast.makeText( inflater.getContext(), "Klikn¹³eœ " + play.getNick(), Toast.LENGTH_SHORT )
					.show();
				}
			});
		}
		return vi;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
