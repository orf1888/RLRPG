package rl_rpg.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class L {
	private static final String tag_normal = "rl_rpg_normal";
	private static final String tag_assert = "rl_rpg_assert";
	private static final String tag_error = "rl_rpg_error";

	public static void log(String s) {
		log(tag_normal, s);
	}

	public static void logError(Exception e) {
		logError(tag_error, e);
	}

	public static void _assert(boolean v) {
		if (!v) {
			log(tag_assert, "assert failed:");
			logError(tag_assert, new Exception());
		}
	}

	private static void log(String tag, String s) {
		android.util.Log.d(tag, s);
	}

	private static void logError(String tag, Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter s = new PrintWriter(sw);
		e.printStackTrace(s);
		log(tag, sw.getBuffer().toString());
	}

}
