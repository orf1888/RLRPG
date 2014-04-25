package rl_rpg.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


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

}
