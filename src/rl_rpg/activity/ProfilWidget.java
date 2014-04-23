package rl_rpg.activity;

import rl_rpg.model.Profil;
import rl_rpg.model.Profil.OnChangeProfilListener;
import rl_rpg.utils.L;
import rl_rpg.utils.Utils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;


public class ProfilWidget
{
	public TextView nick, lvl, xp;
	public ImageButton myAccount;
	private boolean canClickMyAccount;

	Activity parent;

	ProfilWidget( Activity parent, boolean canClickMyAccount )
	{
		this.parent = parent;
		this.canClickMyAccount = canClickMyAccount;
	}

	// funkcja wykonywana automatycznie dla kazdej zmiany zachodzacej w profil
	public void updateProfil()
	{
		nick.setText( "NICK: _" + Profil.getLocal().getNick() );
		lvl.setText( "LVL: " + Profil.getLocal().getLvl() );
		xp.setText( "XP: " + Profil.getLocal().getXp() );
	}

	public void setListeners()
	{
		final Context context = parent.getApplicationContext();
		/////////////////////////
		//// onChange
		Profil.getLocal().addOnChangeListener( "ProfilWidget", new OnChangeProfilListener()
		{
			@Override
			public void onChange()
			{
				parent.runOnUiThread( new Runnable()
				{
					@Override
					public void run()
					{
						updateProfil();
					}
				} );
			}
		} );

		/////////////////////////
		//// onClick
		if( canClickMyAccount ) {
			myAccount.setOnClickListener( new OnClickListener()
			{
				@Override
				public void onClick( View v )
				{
					Utils.startNewActivity(context, MyAccountActivity.class);
				}
			} );
		}
	}
	
	public void dropListeners()
	{
		Profil.getLocal().removeOnChangeListener("ProfilWidget");
	}
}
