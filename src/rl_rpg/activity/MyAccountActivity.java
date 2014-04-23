package rl_rpg.activity;

import java.util.ArrayList;

import rl_rpg.activity.adapter.SkillsListAdapter;
import rl_rpg.model.Profil;
import rl_rpg.model.SkillListModel;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MyAccountActivity extends Activity
{
	ProgressBar p1, p2, p3;
	TextView nick, lvl, xp;
	/* Skills list */
	ListView list;
	SkillsListAdapter adapter;
	public ArrayList<SkillListModel> CustomListViewValuesArr;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{

		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_my_account );

		nick = (TextView) findViewById( R.id.textMyAccNick );
		lvl = (TextView) findViewById( R.id.textMyAccLvl );
		xp = (TextView) findViewById( R.id.textMyAccXp );
		updateProfil();
		/* Skill list init */
		CustomListViewValuesArr = new ArrayList<SkillListModel>();
		Resources res = getResources();
		adapter = new SkillsListAdapter( this, CustomListViewValuesArr, res );

		list = (ListView) findViewById( R.id.skillsList );
		setListData();
		list = (ListView) findViewById( R.id.skillsList );
		list.setAdapter( adapter );

	}


	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.my_account, menu );
		return true;
	}

	public void updateProfil()
	{
		nick.setText( "NICK: " + Profil.getLocal().getNick() );
		lvl.setText( "LVL: " + Profil.getLocal().getLvl() );
		xp.setText( "XP: " + Profil.getLocal().getXp() );
	}

	public void setListData()
	{
		SkillListModel superSkill = new SkillListModel();
		superSkill.setSkillName( "Coding");
		superSkill.setSkillDescr( "In this skill you should write a lot of lines of code;)" );
		CustomListViewValuesArr.add( superSkill );
		for (int i = 1; i < 22; i++) {
			final SkillListModel skill = new SkillListModel();

			/* Set model data */
			skill.setSkillName( "Skill " + i );
			skill.setSkillDescr( "This skill... Tu generalnie powinieen by� opis zasysany z DB" );
			int prog = 0 + (int) (Math.random() * 100);
			skill.setSkillValue( prog );
			/* Add object */
			CustomListViewValuesArr.add( skill );
		}
	}

	public void onItemClick( int itemPos )
	{
		final SkillListModel skill = (SkillListModel) CustomListViewValuesArr.get( itemPos );
		/* After click dialog */
		AlertDialog.Builder builder = new AlertDialog.Builder( this );
		builder.setMessage( skill.getSkillDescr() );
		builder.setTitle( skill.getSkillName() );
		/* Doda� "Ok" do strings�w */
		builder.setPositiveButton( "Train", new DialogInterface.OnClickListener()
		{
			public void onClick( DialogInterface dialog, int id )
			{
				SkillTainingDialog improveDialog=new SkillTainingDialog( MyAccountActivity.this);
				improveDialog.buildDialog( skill.getSkillName(), skill.getSkillDescr() );
			}
		} );
		builder.setNegativeButton( "OK", new DialogInterface.OnClickListener()
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
