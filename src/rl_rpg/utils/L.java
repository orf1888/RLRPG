package rl_rpg.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class L
{
	private static final String tag = "rl_rpg";

	public static void log( String s )
	{
		android.util.Log.d( tag, s );
	}
	
	public static void logError( Exception e ){
		StringWriter sw = new StringWriter();
		PrintWriter s = new PrintWriter(sw);
		e.printStackTrace(s);
		log( sw.getBuffer().toString() );
	}
}
