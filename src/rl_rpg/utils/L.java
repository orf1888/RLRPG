package rl_rpg.utils;



public class L
{
	private static final String tag_normal= "rl_rpg_normal";
	private static final String tag_assert= "rl_rpg_assert";
	private static final String tag_error= "rl_rpg_error";

	public static void log( String s )
	{
		android.util.Log.i( tag_normal, s );
	}

	public static void logError( Exception e )
	{
		android.util.Log.e( tag_error, "error", e );
	}

	public static void _assert( boolean v )
	{
		if( !v ) {
			android.util.Log.i( tag_assert, "assert", new Exception() );
		}
	}
}
