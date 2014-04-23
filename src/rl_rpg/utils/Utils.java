package rl_rpg.utils;

import rl_rpg.activity.CommunityActivity;
import android.content.Context;
import android.content.Intent;

public class Utils {

	public static void startNewActivity(Context context, Class<?> className) {
		Intent intent = new Intent(context, CommunityActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

}