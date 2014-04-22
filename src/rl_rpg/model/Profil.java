package rl_rpg.model;

import java.util.ArrayList;


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
	public void update( ProfilDelta d )
	{
		raiseOnChanged();
	}
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
		static Profil local = null;
		
		public static Profil load()
		{
			Profil p = new Profil();
			p.nick = "Kutnapletes";
			p.lvl = -2;
			p.xp = 23;
			return p;
		}
		
		public static Profil getLocalProfil()
		{
			if( local==null)
				local= load();
			return local;
		}
		
		public static void update(){
			if( getLocalProfil().changed ){
				local.changed= false;
				local.raiseOnChanged();
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
		this.changed= true;
	}

	public int getLvl()
	{
		return lvl;
	}

	public void setLvl( int lvl )
	{
		this.lvl = lvl;
		this.changed= true;
	}

	public int getXp()
	{
		return xp;
	}

	public void setXp( int xp )
	{
		this.xp = xp;
		this.changed= true;
	}
}
