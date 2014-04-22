package rl_rpg.model;

import java.util.ArrayList;


//dupa 1
public class Profil
{
	private String nick;
	private int lvl;
	private int xp;

	///
	/// pomocnicze
	///

	ArrayList<OnChangeProfilListener> onChangeListeners = new ArrayList<Profil.OnChangeProfilListener>();

	///
	/// methods
	///

	public void addOnChangeListener( OnChangeProfilListener listener )
	{
		onChangeListeners.add( listener );
	}

	private void raiseOnChanged()
	{
		for (OnChangeProfilListener listener : onChangeListeners)
			listener.onChange();
	}

	public void update( ProfilDelta d )
	{
		raiseOnChanged();
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
		public static Profil load()
		{
			Profil p = new Profil();
			p.nick = "Kutnapletes";
			p.lvl = -2;
			p.xp = 23;
			return p;
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
	}

	public int getLvl()
	{
		return lvl;
	}

	public void setLvl( int lvl )
	{
		this.lvl = lvl;
	}

	public int getXp()
	{
		return xp;
	}

	public void setXp( int xp )
	{
		this.xp = xp;
	}
}
