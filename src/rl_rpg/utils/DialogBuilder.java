package rl_rpg.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;


public class DialogBuilder extends AlertDialog
{
	private static Builder builder;

	protected DialogBuilder( Context context, String title, String message, OnClickListener posButListener,
			OnClickListener negButListener, String posButtonText, String negButtonText )
	{
		super( context );
		builder = new Builder( context );
		builder.setTitle( title );
		builder.setMessage( message );
		builder.setPositiveButton( posButtonText, posButListener );
		builder.setNegativeButton( negButtonText, negButListener );
	}

	public void setView(View view){
		builder.setView( view );
	}
	
	public static void showDialog()
	{
		builder.show();
	}
}
