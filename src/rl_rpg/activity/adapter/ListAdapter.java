package rl_rpg.activity.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


@SuppressWarnings("rawtypes")
abstract class ListAdapter extends BaseAdapter //implements OnClickListener
{
	Activity activity;

	List data;
	LayoutInflater inflater;
	Resources res;

	int RLayoutItem;

	abstract OnClickListener getOnRowClickListener();

	abstract IViewHolder createViewHolder( View vi );

	public ListAdapter( Activity a, List d, Resources r, int RLayoutItem )
	{
		activity= a;
		data= d;
		res= r;
		this.RLayoutItem= RLayoutItem;
		inflater= (LayoutInflater) activity.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
	}

	@Override
	public int getCount()
	{
		if( data.size() <= 0 )
			return 1;
		return data.size();
	}

	@Override
	public Object getItem( int position )
	{
		return position;
	}

	@Override
	public long getItemId( int position )
	{
		return position;
	}

	interface IViewHolder
	{
		void clear();

		void set( Object model, int position );
	}


	public View getView( int position, View convertView, ViewGroup parent )
	{
		View vi= convertView;
		IViewHolder holder;

		if( convertView == null ) {
			vi= inflater.inflate( RLayoutItem, null );

			holder= createViewHolder( vi );
			vi.setTag( holder );
		} else {
			holder= (IViewHolder) vi.getTag();
		}

		if( data.size() <= position ) {
			holder.clear();
		} else {
			holder.set( data.get( position ), position );

			vi.setOnClickListener( getOnRowClickListener() );
		}
		return vi;
	}
}