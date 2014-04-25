package rl_rpg.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.LinearLayout;


public class Utils
{

	public static void startNewActivity( Context context, Class<?> className, Bundle bundle )
	{
		Intent intent= new Intent( context, className );
		intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
		if( bundle != null )
			intent.putExtras( bundle );

		context.startActivity( intent );
	}

	public static int getScreenWidth( Activity parent )
	{
		DisplayMetrics screenSize= new DisplayMetrics();
		parent.getWindowManager().getDefaultDisplay().getMetrics( screenSize );
		return screenSize.widthPixels;
	}

	/***
	 * Method set button width screenWidth/2 - margins.
	 * Use only when you have 2 buttons in 1 line!
	 * @param button - Button
	 * @param parent - Activity
	 */
	public static void setButtonWidth( Button button, Activity parent )
	{
		button.setLayoutParams( new LinearLayout.LayoutParams( (getScreenWidth( parent ) / 2) - /*Tu powinniœmy pobieraæ marginesy*/
		25, /*wysokosc*/75 ) );
	}

}
