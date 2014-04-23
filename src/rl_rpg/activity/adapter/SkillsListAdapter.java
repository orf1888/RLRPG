package rl_rpg.activity.adapter;

import java.util.ArrayList;

import rl_rpg.activity.ChallengeListViewActivity;
import rl_rpg.activity.MyAccountActivity;
import rl_rpg.activity.R;
import rl_rpg.activity.SkillsListViewActivity;
import rl_rpg.model.SkillListModel;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;


public class SkillsListAdapter extends BaseAdapter implements OnClickListener
{

	private Activity activity;
	/* Docelowo zamaist String powinny byæ Skille */
	private ArrayList<?> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	int i = 0;

	public SkillsListAdapter( Activity activity, ArrayList<?> data, Resources res )
	{
		this.activity = activity;
		this.data = data;
		this.res = res;
		inflater = (LayoutInflater) activity.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

	}

	@Override
	public int getCount()
	{
		if( data.size() <= 0 )
			return 1;
		else
			return data.size();
	}

	public Object getItem( int position )
	{
		return position;
	}

	public long getItemId( int position )
	{
		return position;
	}

	public static class ViewHolder
	{
		ViewHolder( View viev )
		{
			skillName = (TextView) viev.findViewById( R.id.textSkillName);
			skillValue = (ProgressBar) viev.findViewById( R.id.progSkill );
		}

		public TextView skillName;
		public ProgressBar skillValue;
	}

	public View getView( int position, View convertView, ViewGroup parent )
	{

		View vi = convertView;
		ViewHolder holder;

		if( convertView == null ) {
			vi = inflater.inflate( R.layout.skill_item, null );
			holder = new ViewHolder( vi );
			vi.setTag( holder );
		} else {
			holder = (ViewHolder) vi.getTag();
		}

		if( data.size() <= 0 ) {
			holder.skillName.setText( "No Skill's!" );

		} else {


			SkillListModel tempValues = (SkillListModel) data.get( position );
			/* Set data on screen */
			holder.skillName.setText( tempValues.getSkillName() );
			holder.skillValue.setProgress( tempValues.getSkillValue() );

			vi.setOnClickListener( new OnItemClickListener( position ) );
		}
		return vi;
	}


	private class OnItemClickListener implements OnClickListener
	{
		private int itemPos;

		OnItemClickListener( int position )
		{
			itemPos = position;
		}

		@Override
		public void onClick( View arg0 )
		{
			MyAccountActivity skill = (MyAccountActivity) activity;
			skill.onItemClick( itemPos );
		}
	}

	@Override
	public void onClick( View v )
	{
		// TODO Auto-generated method stub

	}

}
