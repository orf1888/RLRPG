package rl_rpg.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;


public class DialogBuilder extends AlertDialog
{
	private static Builder builder;

	public DialogBuilder( Context context, String title, String message, OnClickListener posButListener,
			OnClickListener negButListener, String posButtonText, String negButtonText )
	{
		super( context );
		builder= new Builder( context );
		builder.setTitle( title );
		builder.setMessage( message );
		// Potrzebujemy dialogu z tylko 1 guzikiem "wróæ/ok"
		if( (posButListener == null && negButListener == null) || negButListener == null || posButListener == null )
			builder.setPositiveButton( posButtonText, createDefaultReturnListener() );
		else {// Potrzebujemy dialogu z dwoma guzikami
			builder.setPositiveButton( posButtonText, posButListener );
			builder.setNegativeButton( negButtonText, negButListener );
		}
	}

	public static OnClickListener createDefaultReturnListener()
	{
		return new OnClickListener()
		{

			@Override
			public void onClick( DialogInterface dialog, int which )
			{
				return;
			}
		};
	}

	public void setView( View view )
	{
		builder.setView( view );
	}

	public void showDialog()
	{
		builder.show();
	}
}
