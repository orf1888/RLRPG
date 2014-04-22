package rl_rpg.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rl_rpg.activity.RLRPGApplication;
import rl_rpg.activity.RLRPGApplication.SaveLoadListener;
import rl_rpg.utils.L;


public class Profil
{
	private String nick;
	private int lvl;
	private int xp;

	///
	/// pomocnicze
	///

	ArrayList<OnChangeProfilListener> onChangeListeners = new ArrayList<Profil.OnChangeProfilListener>();
	boolean changed;

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
	/// classes
	///

	public static class ProfilDelta
	{

	}

	public static interface OnChangeProfilListener
	{
		public void onChange();
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
			p.nick = "x";
			p.lvl = 1;
			p.xp = 0;
			p.changed = true;
			return p;
		}

		static Manager _instance = null;

		static Manager instance()
		{
			if( _instance == null ) {
				_instance = new Manager();
			}
			return _instance;
		}

		Manager()
		{
			RLRPGApplication.addSaveLoadListener( new ProfilSaveLoadListener() );
		}

		static class ProfilSaveLoadListener implements SaveLoadListener
		{

			@Override
			public Map<String, Object> onSave()
			{
				Map<String, Object> save = new HashMap<String, Object>();
				Profil local = Manager.getLocalProfil();
				save.put( "local_nick", local.nick );
				save.put( "local_lvl", local.lvl );
				save.put( "local_xp", local.xp );
				return save;
			}

			@Override
			public void onLoad( Map<String, Object> save )
			{
				Profil local = Manager.getLocalProfil();
				local.nick = (String) save.get( "local_nick" );
				local.lvl = (int) (Integer) save.get( "local_lvl" );
				local.xp = (int) (Integer) save.get( "local_xp" );
				local.changed = true;
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
