package rl_rpg.utils;

import android.content.Context;
import android.content.Intent;


public class Utils
{

	public static void startNewActivity( Context context, Class<?> className )
	{
		Intent intent= new Intent( context, className );
		intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
		context.startActivity( intent );
	}

}
