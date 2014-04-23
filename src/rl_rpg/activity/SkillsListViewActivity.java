package rl_rpg.activity;

import java.util.ArrayList;

import rl_rpg.activity.adapter.SkillsListAdapter;
import rl_rpg.model.ChallengeListModel;
import rl_rpg.model.SkillListModel;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;


public class SkillsListViewActivity extends Activity
{
	ListView list;
	SkillsListAdapter adapter;
	public SkillsListViewActivity CustomListView = null;
	public ArrayList<SkillListModel> CustomListViewValuesArr = new ArrayList<SkillListModel>();

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.skills_list );

		CustomListView = this;
		setListData();

		Resources res = getResources();
		list = (ListView) findViewById( R.id.skillsList );
		adapter = new SkillsListAdapter( CustomListView, CustomListViewValuesArr, res );
		list.setAdapter( adapter );

	}

	public void setListData()
	{
		for (int i = 0; i < 22; i++) {
			final SkillListModel skill = new SkillListModel();

			/* Set model data */
			skill.setSkillName( "Skill " + i );
			skill.setSkillDescr( "This skill... Tu generalnie powinieen byæ opis zasysany z DB" );
			int prog = 0 + (int) (Math.random() * 100);
			skill.setSkillValue( prog );
			/* Add object */
			CustomListViewValuesArr.add( skill );
		}
	}

	public void onItemClick( int itemPos )
	{
		SkillListModel skill = (SkillListModel) CustomListViewValuesArr.get( itemPos );
		/*After click dialog*/
		AlertDialog.Builder builder = new AlertDialog.Builder( this );
		builder.setMessage(skill.getSkillDescr() );
		builder.setTitle( skill.getSkillName() );
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
