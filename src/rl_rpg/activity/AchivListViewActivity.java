package rl_rpg.activity;

import java.util.ArrayList;

import rl_rpg.activity.adapter.AchivListAdapter;
import rl_rpg.model.AchivListModel;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;


public class AchivListViewActivity extends Activity
{

	ListView list;
	AchivListAdapter adapter;
	public AchivListViewActivity CustomListView = null;
	public ArrayList<AchivListModel> CustomListViewValuesArr = new ArrayList<AchivListModel>();

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{


		super.onCreate( savedInstanceState );
		setContentView( R.layout.achiv_list );

		CustomListView = this;

		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
		setListData();

		Resources res = getResources();
		list = (ListView) findViewById( R.id.list ); // List defined in XML ( See Below )

		/**************** Create Custom Adapter *********/
		adapter = new AchivListAdapter( CustomListView, CustomListViewValuesArr, res );
		list.setAdapter( adapter );

	}

	/****** Function to set data in ArrayList *************/
	public void setListData()
	{

		for (int i = 0; i < 11; i++) {

			final AchivListModel sched = new AchivListModel();

			/******* Firstly take data in model object ******/
			sched.setAchivName( "Achivment" + i );
			//sched.setImage( "image" + i );
			sched.setAchivDescr( "This achivment value is 100 XP" );

			/******** Take Model Object in ArrayList **********/
			CustomListViewValuesArr.add( sched );
		}

	}


	/*****************  This function used by adapter ****************/
	public void onItemClick( int mPosition )
	{
		AchivListModel achivment = (AchivListModel) CustomListViewValuesArr.get( mPosition );
		/* Show achivment description */
		AlertDialog.Builder builder = new AlertDialog.Builder( this );
		builder.setMessage( "You need to etc.\n" + achivment.getAchivDescr() );
		/* Dodaæ "Ok" do stringsów */
		builder.setPositiveButton( "OK", new DialogInterface.OnClickListener()
		{
			public void onClick( DialogInterface dialog, int id )
			{
				return;
			}
		} );
		AlertDialog dialog = builder.create();
		dialog.show();
	}
}
