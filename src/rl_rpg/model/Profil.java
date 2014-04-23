package rl_rpg.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rl_rpg.activity.RLRPGApplication;
import rl_rpg.activity.RLRPGApplication.SaveLoadListener;
import rl_rpg.utils.L;
import rl_rpg.utils.MapWithDefaults;


public class Profil
{
	///
	/// methods
	///

	public void addOnChangeListener( OnChangeProfilListener listener )
	{
		onChangeListeners.add( listener );
	}

	/*
	 * public void update( ProfilDelta d ) { raiseOnChanged(); }
	 */

	///
	/// static methods
	///

	public static Profil getLocal()
	{
		return Manager.getLocalProfil();
	}

	///
	/// private methods
	///

	private void raiseOnChanged()
	{
		for (OnChangeProfilListener listener : onChangeListeners)
			listener.onChange();
	}

	///
	/// model
	///

	private String nick;
	private int lvl;
	private int xp;

	///
	/// pomocnicze zmienne
	///

	ArrayList<OnChangeProfilListener> onChangeListeners = new ArrayList<Profil.OnChangeProfilListener>();
	boolean changed;

	private final String default_nick = "x1";
	private final int default_lvl = 20;
	private final int default_xp = 0;


	///
	/// classes
	///

	public static class _ProfilDelta
	{

	}

	public static interface OnChangeProfilListener
	{
		public void onChange();
	}


	Object save()
	{
		Map<String, Object> save = new HashMap<String, Object>( 3 );
		save.put( "nick", nick );
		save.put( "lvl", lvl );
		save.put( "xp", xp );
		return save;
	}

	void load( MapWithDefaults save )
	{
		nick = (String) save.get( "nick", default_nick );
		lvl = (int) (Integer) save.get( "lvl", default_lvl );
		xp = (int) (Integer) save.get( "xp", default_xp );
		changed = true;
	}


	public static class Manager
	{
		Profil local = null;


		public static Profil getLocalProfil()
		{
			if( instance().local == null )
				instance().local = createDefault();
			return instance().local;
		}

		public static void update()
		{
			Profil local = getLocalProfil();
			if( local.changed ) {
				local.changed = false;
				local.raiseOnChanged();
			}
		}

		static Profil createDefault()
		{
			Profil p = new Profil();
			p.load( MapWithDefaults.defaults() );
			return p;
		}

		private static final Manager INSTANCE = new Manager();

		public static Manager instance()
		{
			return INSTANCE;
		}


		private Manager()
		{
			RLRPGApplication.addSaveLoadListener( new ProfilSaveLoadListener() );
		}

		static class ProfilSaveLoadListener implements SaveLoadListener
		{

			@Override
			public Map<String, Object> onSave()
			{
				Map<String, Object> save = new HashMap<String, Object>( 1 );
				Profil local = Manager.getLocalProfil();
				save.put( "local", local.save() );
				
				return save;
			}

			@Override
			public void onLoad( MapWithDefaults save )
			{
				Profil local = Manager.getLocalProfil();
				local.load( save.getMap( "local" ));
				///
				++local.lvl;
				///
			}

			@Override
			public String getNamePrefix()
			{
				return "Profil";
			}
		}
	}

	///
	/// setters getters
	///

	public String getNick()
	{
		return nick;
	}

	public void setNick( String nick )
	{
		this.nick = nick;
		this.changed = true;
	}

	public int getLvl()
	{
		return lvl;
	}

	public void setLvl( int lvl )
	{
		this.lvl = lvl;
		this.changed = true;
	}

	public int getXp()
	{
		return xp;
	}

	public void setXp( int xp )
	{
		this.xp = xp;
		this.changed = true;
	}
}
