package rl_rpg.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Utils {

	public static void startNewActivity(Context context, Class<?> className,
			int profilID) {
		Intent intent = new Intent(context, className);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// poprawic
		if (profilID > -1) {
			Bundle b = new Bundle();
			b.putInt("ProfilID", profilID);
			intent.putExtras(b);
		}
		context.startActivity(intent);
	}

}
