package rl_rpg.activity;

import rl_rpg.utils.DialogBuilder;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SkillTainingDialog extends DialogBuilder
{
	static EditText skillImproveValue;
	
	public SkillTainingDialog( Context context, String title, String message, OnClickListener posButListener,
			OnClickListener negButListener, String posButtonText, String negButtonText )
	{
		super( context, title, message, posButListener, negButListener, posButtonText, negButtonText );
		skillImproveValue = new EditText( context );
		skillImproveValue.setHint( "Enter the value" );
	}
	/*static Builder builder;
	static EditText skillImproveValue;

	public SkillTainingDialog( Context context )
	{
		super( context );
		builder = new Builder( context );
		skillImproveValue = new EditText( context );
		skillImproveValue.setHint( "Enter the value" );
	}

	@SuppressLint("NewApi")
	public static void buildDialog( final String skillName, String skillImproveMissionText )
	{
		builder.setMessage( skillImproveMissionText );
		builder.setTitle( skillName );
		builder.setView( skillImproveValue );
		builder.setPositiveButton( "Submit", new OnClickListener()
		{

			@Override
			public void onClick( DialogInterface dialog, int which )
			{
				 Tu narazie prowizoryczna funkcja 
				int skillXP=0;
				try {
					skillXP=Integer.parseInt( skillImproveValue.getText().toString());
				} catch ( Exception e ) {
					Toast.makeText( builder.getContext(),
							"You fucked up so simple thing! You are so fucking idot:D",
							Toast.LENGTH_LONG).show();
					return;
				}
				Toast.makeText( builder.getContext(),
						"You increase " + skillName + " lvl by " + skillXP + " points!",
						Toast.LENGTH_SHORT ).show();

			}
		} );
		builder.setNegativeButton( "Back", new OnClickListener()
		{

			@Override
			public void onClick( DialogInterface dialog, int which )
			{
				return;
			}
		} );
		builder.show();
	}*/
}
