package rl_rpg.activity;

import rl_rpg.model.Profil;
import rl_rpg.model.Profil.OnChangeProfilListener;
import rl_rpg.utils.Utils;
import android.app.Activity;
import android.content.Context;
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
		this.parent= parent;
		this.canClickMyAccount= canClickMyAccount;
	}

	/**
	 * funkcja wykonywana automatycznie dla kazdej zmiany zachodzacej w profil
	 * dodana w listenerze do modelu - profilu
	 */
	public void updateProfil()
	{
		nick.setText( "NICK: " + Profil.getLocal().getNick() );
		lvl.setText( "LVL: " + Profil.getLocal().getLvl() );
		xp.setText( "XP: " + Profil.getLocal().getXp() );
	}

	/**
	 * ustawia ewentualne listenery. Uwaga, jesli cos jest wywolane poza w¹tkiem
	 * UI, musi byc uzyte activity.runOnUiThread( new Runnable() Uwaga, jesli
	 * jakis listener odwo³uje siê poza UI (np. do modelu), trzeba go wywaliæ w
	 * funkcji dropListeners
	 */
	public void setListeners()
	{
		final Context context= parent.getApplicationContext();
		// ///////////////////////
		// // onChange
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

		// ///////////////////////
		// // onClick
		if( canClickMyAccount ) {
			myAccount.setOnClickListener( new OnClickListener()
			{
				@Override
				public void onClick( View v )
				{
					Utils.startNewActivity( context, MyAccountActivity.class );
				}
			} );
		}
	}

	/**
	 * tutaj usuwamy listenery, jesli dodaliœmy jakieœ do modelu
	 */
	public void dropListeners()
	{
		Profil.getLocal().removeOnChangeListener( "ProfilWidget" );
	}
}
