package rl_rpg.activity;

import java.util.ArrayList;

import rl_rpg.activity.adapter.ChallengeListAdapter;
import rl_rpg.model.ChallengeListModel;
import rl_rpg.utils.DialogBuilder;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;

public class ChallengeListViewActivity extends Activity {

	ListView list;
	ChallengeListAdapter adapter;
	public ChallengeListViewActivity CustomListView = null;
	public ArrayList<ChallengeListModel> CustomListViewValuesArr = new ArrayList<ChallengeListModel>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.challenge_list);

		CustomListView = this;

		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
		setListData();

		Resources res = getResources();
		list = (ListView) findViewById(R.id.list); // List defined in XML ( See
													// Below )

		/**************** Create Custom Adapter *********/
		adapter = new ChallengeListAdapter(CustomListView,
				CustomListViewValuesArr, res);
		list.setAdapter(adapter);

	}

	/****** Function to set data in ArrayList *************/
	public void setListData() {

		for (int i = 0; i < 11; i++) {

			final ChallengeListModel sched = new ChallengeListModel();

			/******* Firstly take data in model object ******/
			sched.setChallengeName("Challeng" + i);
			// sched.setImage( "image" + i );
			sched.setChallengeDescr("This challenge value is 100 XP");

			/******** Take Model Object in ArrayList **********/
			CustomListViewValuesArr.add(sched);
		}

	}

	/***************** This function used by adapter ****************/
	public void onItemClick(int mPosition) {
		ChallengeListModel challenge = (ChallengeListModel) CustomListViewValuesArr
				.get(mPosition);
		// To jest w�asnie sytuacja kiedy potrzebujemy w dialogu tylko jednego
		// przycisku, kt�ry wraca do Activity
		DialogBuilder dialog = new DialogBuilder(
				ChallengeListViewActivity.this, challenge.getChallengeName(),
				challenge.getChallengeDescr(), null, null, "OK", null);
		dialog.showDialog();
	}
}
