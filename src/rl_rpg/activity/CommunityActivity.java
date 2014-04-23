package rl_rpg.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class CommunityActivity extends Activity {

	TextView regPlayers, onlPlayers, onlFriends;
	Button friend, searchPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_community);

		//init buttons
		regPlayers=(TextView) findViewById(R.id.textCommRegPlay);
		onlPlayers=(TextView) findViewById(R.id.textCommOnlPlay);
		onlFriends=(TextView) findViewById(R.id.textCommOnlFrie);
		friend=(Button) findViewById(R.id.btnFriend);
		searchPlayer=(Button) findViewById(R.id.btnSearchPlay);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.community, menu);
		return true;
	}

}
