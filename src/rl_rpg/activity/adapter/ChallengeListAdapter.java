package rl_rpg.activity.adapter;

import java.util.ArrayList;

import rl_rpg.activity.ChallengeListViewActivity;
import rl_rpg.activity.R;
import rl_rpg.model.ChallengeListModel;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ChallengeListAdapter extends BaseAdapter implements OnClickListener
{

	/*********** Declare Used Variables *********/
	private Activity activity;
	private ArrayList data;
	private static LayoutInflater inflater = null;
	public Resources res;
//	AchivListModel tempValues = null;
	int i = 0;

	/*************  CustomAdapter Constructor *****************/
	public ChallengeListAdapter( Activity a, ArrayList d, Resources resLocal )
	{

		/********** Take passed values **********/
		activity = a;
		data = d;
		res = resLocal;

		/***********  Layout inflator to call external xml layout () ***********/
		inflater = (LayoutInflater) activity.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

	}

	/******** What is the size of Passed Arraylist Size ************/
	public int getCount()
	{

		if( data.size() <= 0 )
			return 1;
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

	/********* Create a holder Class to contain inflated xml file elements *********/
	public static class ViewHolder
	{
		ViewHolder(View viev){
			challengeName = (TextView) viev.findViewById( R.id.challengeName );
			challengeDescrip = (TextView) viev.findViewById( R.id.challengeDesription );
			image = (ImageView) viev.findViewById( R.id.challengeImg );
			challengeStart = (Button) viev.findViewById( R.id.challengeStart );
		}
		public TextView challengeName;
		public TextView challengeDescrip;
		//public TextView textWide;
		public ImageView image;
		public Button challengeStart;

	}

	/****** Depends upon data size called for each row , Create each ListView row *****/
	public View getView( int position, View convertView, ViewGroup parent )
	{

		View vi = convertView;
		ViewHolder holder;

		if( convertView == null ) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			vi = inflater.inflate( R.layout.challenge_item, null );

			/****** View Holder Object to contain tabitem.xml file elements ******/
			holder = new ViewHolder(vi);

			/************  Set holder with LayoutInflater ************/
			vi.setTag( holder );
		} else{
			holder = (ViewHolder) vi.getTag();
		}

		if( data.size() <= 0 ) {
			holder.challengeName.setText( "No Data" );

		} else {
			/***** Get each Model object from Arraylist ********/
//			tempValues = null;
//			tempValues = (AchivListModel) data.get( position );

			/************  Set Model values in Holder elements ***********/

			ChallengeListModel tempValues= (ChallengeListModel) data.get( position );
			
			holder.challengeName.setText( tempValues.getChallengeName() );
			holder.challengeDescrip.setText( tempValues.getChallengeDescr() );
			holder.challengeStart.setTag( new Integer(position) );
			holder.challengeStart.setOnClickListener( new OnClickListener()
			{

				@Override
				public void onClick( View v )
				{
					Integer position= (Integer) v.getTag();
					ChallengeListModel arch = (ChallengeListModel) data.get( position );
					//String x = holder.challengeName.getText().toString();
					Toast.makeText( inflater.getContext(), "Zacz¹³eœ " + arch.getChallengeName(), Toast.LENGTH_SHORT )
							.show();
				}
			} );

			/******** Set Item Click Listner for LayoutInflater for each row *******/

			vi.setOnClickListener( new OnItemClickListener( position ) );
		}
		return vi;
	}

	@Override
	public void onClick( View v )
	{
		Log.v( "CustomAdapter", "=====Row button clicked=====" );
	}

	/********* Called when Item click in ListView ************/
	private class OnItemClickListener implements OnClickListener
	{
		private int mPosition;

		OnItemClickListener( int position )
		{
			mPosition = position;
		}

		@Override
		public void onClick( View arg0 )
		{
			ChallengeListViewActivity sct = (ChallengeListViewActivity) activity;
			/****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/

			sct.onItemClick( mPosition );
		}
	}
}
