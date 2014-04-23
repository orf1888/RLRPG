package rl_rpg.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rl_rpg.activity.adapter.SkillsListAdapter;
import rl_rpg.model.Profil;
import rl_rpg.model.Skill;
import rl_rpg.utils.DialogBuilder;
import rl_rpg.utils.L;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MyAccountActivity extends Activity
{
	static ProfilWidget ekranProfil;
	
	ProgressBar p1, p2, p3;
	//TextView nick, lvl, xp;
	/* Skills list */
	ListView list;
	SkillsListAdapter adapter;
	public ArrayList<Skill> CustomListViewValuesArr;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		
		ekranProfil = new ProfilWidget( this, false );
		
		setContentView( R.layout.activity_my_account );

		ekranProfil.nick = (TextView) findViewById( R.id.textMyAccNick );
		ekranProfil.lvl = (TextView) findViewById( R.id.textMyAccLvl );
		ekranProfil.xp = (TextView) findViewById( R.id.textMyAccXp );
		ekranProfil.updateProfil();
		
		/* Skill list init */
		CustomListViewValuesArr = new ArrayList<Skill>();
		Resources res = getResources();
		adapter = new SkillsListAdapter( this, CustomListViewValuesArr, res );

		list = (ListView) findViewById( R.id.skillsList );
		setListData();
		list = (ListView) findViewById( R.id.skillsList );
		list.setAdapter( adapter );
	}

	
	@Override
	protected void onPause()
	{
		super.onPause();
		ekranProfil.dropListeners();
		
		try {
			RLRPGApplication.performSave();
		} catch ( IOException e ) {
			L.logError( e );
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		ekranProfil.setListeners();
		ekranProfil.updateProfil();
	}
	

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.my_account, menu );
		return true;
	}

	public void setListData()
	{
		List<Skill> skills = Profil.getLocal().getSkills();
		for( Skill s : skills ){
			CustomListViewValuesArr.add( s );
		}
	}

	public void onItemClick( int itemPos )
	{
		final Skill skill = (Skill) CustomListViewValuesArr.get( itemPos );
		//Listenery
		final OnClickListener returnListener=new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		};
		final OnClickListener trainListener=new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				SkillTainingDialog improveDialog=new SkillTainingDialog( MyAccountActivity.this, skill.getName(), "How many lines of code did you wrote today?", null, null, "Submit", "Back", new EditText( MyAccountActivity.this ) );
				improveDialog.showDialog();	
			}
		};
		DialogBuilder dialog=new DialogBuilder(MyAccountActivity.this, skill.getName(), skill.getDescr(), trainListener, returnListener, "Train", "Back");
		dialog.showDialog();
		
	}

}
