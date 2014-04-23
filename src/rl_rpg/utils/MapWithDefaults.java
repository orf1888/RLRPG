package rl_rpg.utils;

import java.util.Map;


/**
 * klasa obudowuje zwyk�� map�. Nie musi ona istnie� (mo�na poda� null)
 * wywo�anie get(key, default) zwraca warto�� z mapy (je�li istnieje) lub warto�c default w pozosta�ym przypadku
 */
public class MapWithDefaults
{
	Map map;

	public static MapWithDefaults defaults()
	{
		return new MapWithDefaults( null );
	}

	public MapWithDefaults( Object map )
	{
		this.map = (Map) map;
	}

	public Object get( String key, Object deflt )
	{
		Object result = null;
		if( map != null )
			result = map.get( key );
		if( result != null )
			return result;
		//else
		if( map != null )
			L.log( "MapWithDefaults: klucz \'" + key + "\' pusty, uzywam default value: " + deflt );
		return deflt;
	}

	public MapWithDefaults getMap( String key )
	{
		Map result = (Map) map.get( key );
		return new MapWithDefaults( result );
	}
}
