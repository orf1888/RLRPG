package rl_rpg.activity.adapter;

import java.util.List;

import rl_rpg.activity.R;
import rl_rpg.activity.SkillTainingDialog;
import rl_rpg.model.Skill;
import rl_rpg.utils.DialogBuilder;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


public class SkillsListAdapter extends ListAdapter
{
	@SuppressWarnings("rawtypes")
	public SkillsListAdapter( Activity a, List d, Resources res )
	{
		super( a, d, res, R.layout.skill_item );
	}
	
	@Override
	OnClickListener getOnRowClickListener()
	{
		return rowOnClickListener;
	}

	@Override
	IViewHolder createViewHolder( View vi )
	{
		return new ViewHolder( vi );
	}

	public static class ViewHolder implements IViewHolder
	{
		ViewHolder( View viev )
		{
			skillName = (TextView) viev.findViewById( R.id.textSkillName );
			skillValue = (ProgressBar) viev.findViewById( R.id.progSkill );
		}

		public TextView skillName;
		public ProgressBar skillValue;
		int position;
		
		@Override
		public void clear()
		{
			skillName.setText( "No Skill" );
		}
		
		@Override
		public void set( Object model, int position )
		{
			this.position= position;
			Skill tempValue = (Skill) model;
			skillName.setText( tempValue.getName() );
			skillValue.setProgress( tempValue.getValue() );
		}
	}
	
	//Listenery
	android.content.DialogInterface.OnClickListener returnListener=new android.content.DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			return;
		}
	};
	
	//android.content.DialogInterface.OnClickListener trainListener=new 
	class SkillDialogOnCliclListener implements android.content.DialogInterface.OnClickListener {
		
		Skill skill;
		
		SkillDialogOnCliclListener( Skill skill ){
			this.skill= skill;
		}
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			SkillTainingDialog improveDialog=new SkillTainingDialog( activity, skill.getName(), "How many lines of code did you wrote today?", null, null, "Submit", "Back", new EditText( activity ) );
			improveDialog.showDialog();	
		}
	};

	OnClickListener rowOnClickListener= new OnClickListener()
	{
		@Override
		public void onClick( View view )
		{
			ViewHolder h= (ViewHolder) view.getTag();

			Skill skill= (Skill) data.get( h.position );
			SkillDialogOnCliclListener trainListener = new SkillDialogOnCliclListener(skill);
			
			DialogBuilder dialog=new DialogBuilder(activity, skill.getName(), skill.getDescr(), trainListener, returnListener, "Train", "Back");
			dialog.showDialog();
		}
	};
}
