package rl_rpg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rl_rpg.activity.RLRPGApplication;
import rl_rpg.activity.RLRPGApplication.LoopListener;
import rl_rpg.activity.RLRPGApplication.SaveLoadListener;
import rl_rpg.model.Skill.SkillType;
import rl_rpg.utils.L;
import rl_rpg.utils.MapWithDefaults;


public class Profil implements Serializable
{
	private static final long serialVersionUID= 1L;

	///
	/// model
	///

	private String nick;
	private int lvl;
	private int xp;
	private List<Skill> skills;
	private int local_id;

	///
	/// model modifiers
	///

	public void addXp( int value )
	{
		L._assert( value > 0 && value < 1000000 );
		xp+= value;
		this.changed= true;
	}

	///
	/// methods
	///

	/** korzystamy z tej funkcji, jesli chcemy byc powiadamiani o zmianach zachodz¹cych w profilu,
	 *  przyklad: UI sie podpina zeby wyswietlac zmiany 
	 * @param string */
	public void addOnChangeListener( String name, OnChangeProfilListener listener )
	{
		onChangeListeners.put( name, listener );
	}

	public void removeOnChangeListener( String name )
	{
		onChangeListeners.remove( name );
	}

	/** pobiera profil osoby aktualnie korzystaj¹cej z aplikacji
	 */
	public static Profil getLocal()
	{
		return Manager.getLocalProfil();
	}

	///
	/// private methods
	///

	private void raiseOnChanged()
	{
		for( OnChangeProfilListener listener : onChangeListeners.values() )
			listener.onChange();
	}

	void raiseSkillChanged()
	{
		raiseOnChanged();
	}

	Object save()
	{
		Map<String, Object> save= new HashMap<String, Object>( 4 + skills.size() );
		save.put( "nick", nick );
		save.put( "lvl", lvl );
		save.put( "xp", xp );
		save.put( "skills_size", skills.size() );
		for( int i= 0; i < skills.size(); ++i ) {
			save.put( "skills_" + i, skills.get( i ).save() );
		}
		save.put( "local_id", local_id );
		return save;
	}

	static Profil load( MapWithDefaults save )
	{
		Profil result= new Profil();
		result.nick= (String) save.get( "nick", default_nick );
		result.lvl= (int) (Integer) save.get( "lvl", default_lvl );
		result.xp= (int) (Integer) save.get( "xp", default_xp );
		result.local_id= (int) (Integer) save.get( "local_id", -1 );

		int skills_count= (int) (Integer) save.get( "skills_size", 0 );
		if( skills_count > 0 ) {
			result.skills= new ArrayList<Skill>( skills_count );
			for( int i= 0; i < skills_count; ++i ) {
				Object skill_save= save.get( "skills_" + i, null );
				Skill skill= null;
				if( skill_save != null )
					skill= Skill.loadOrNull( new MapWithDefaults( skill_save ), result );
				if( skill != null )
					result.skills.add( skill );
			}
		} else {
			result.skills= default_skills( result );
		}
		result.changed= true;
		return result;
	}

	///
	/// pomocnicze zmienne
	///

	Map<String, OnChangeProfilListener> onChangeListeners= new HashMap<String, Profil.OnChangeProfilListener>();
	boolean changed;

	private static final String default_nick= "x1";
	private static final int default_lvl= 20;
	private static final int default_xp= 0;

	private static final ArrayList<Skill> default_skills( Profil parent )
	{
		ArrayList<Skill> result= new ArrayList<Skill>();
		result.add( new Skill( SkillType.DoingNothing, parent ) );
		result.add( new Skill( SkillType.Coding, parent ) );
		result.add( new Skill( SkillType.Test1, parent ) );
		result.add( new Skill( SkillType.Test2, parent ) );
		result.add( new Skill( SkillType.Test1, parent ) );
		result.add( new Skill( SkillType.Test1, parent ) );
		result.add( new Skill( SkillType.Test1, parent ) );
		return result;
	}

	Profil()
	{
	}

	/** pomocnicze */
	Profil( String nick, int lvl, int xp, /**!!!*/
	boolean toJestPomocniczyKonstruktor )
	{
		this.nick= nick;
		this.lvl= lvl;
		this.xp= xp;
	}

	///
	/// classes
	///


	public static interface OnChangeProfilListener
	{
		public void onChange();
	}


	public static class Manager
	{
		ArrayList<Profil> data= null;


		protected static Profil getLocalProfil()
		{
			return instance()._getProfilById( 0 );
		}

		public void init()
		{
			data= new ArrayList<Profil>();
		}

		public void loadDefaults()
		{
			data.clear();

			Profil local= Profil.load( MapWithDefaults.defaults() );

			putInData( local );

			//
			{
				Profil tmp1= new Profil( "zdzisiek", 17, 1, true );
				ArrayList<Skill> skills1= new ArrayList<Skill>();
				skills1.add( new Skill( SkillType.DoingNothing, tmp1 ) );
				skills1.add( new Skill( SkillType.Test1, tmp1 ) );
				skills1.get( 1 ).addValue( 50 );
				tmp1.skills= skills1;
				putInData( tmp1 );
			}
			{
				Profil tmp2= new Profil( "franek", 3, 1, true );
				ArrayList<Skill> skills2= new ArrayList<Skill>();
				skills2.add( new Skill( SkillType.DoingNothing, tmp2 ) );
				skills2.add( new Skill( SkillType.Coding, tmp2 ) );
				skills2.get( 1 ).addValue( 30 );
				tmp2.skills= skills2;
				putInData( tmp2 );
			}
			{
				Profil tmp3= new Profil( "kapitan dupa", 9000, 1, true );
				ArrayList<Skill> skills3= new ArrayList<Skill>();
				skills3.add( new Skill( SkillType.DoingNothing, tmp3 ) );
				skills3.add( new Skill( SkillType.Test1, tmp3 ) );
				skills3.add( new Skill( SkillType.Test1, tmp3 ) );
				skills3.add( new Skill( SkillType.Test2, tmp3 ) );
				skills3.get( 0 ).addValue( 90 );
				skills3.get( 1 ).addValue( 80 );
				tmp3.skills= skills3;
				putInData( tmp3 );
			}//
		}

		private void putInData( Profil profil )
		{
			data.add( profil );
			profil.local_id= data.size() - 1;
		}
		
		public static int getProfilsCount()
		{
			return instance().data.size();
		}

		public static Profil getProfilById( int local_id )
		{
			return instance()._getProfilById( local_id );
		}

		private Profil _getProfilById( int local_id )
		{
			if( data.size() < local_id )
				return null;

			return data.get( local_id );
		}

		//
		// singleton
		//

		private static final Manager INSTANCE= new Manager();

		public static Manager instance()
		{
			return INSTANCE;
		}

		private Manager()
		{
			RLRPGApplication.addSaveLoadListener( new ProfilSaveLoadListener() );
			RLRPGApplication.addLoopListener( new ProfilLoopListener() );
		}

		//
		// listenery
		//

		static class ProfilLoopListener implements LoopListener
		{
			@Override
			public void onTick()
			{
				Profil local= getLocalProfil();
				if( local.changed ) {
					local.changed= false;
					local.raiseOnChanged();
				}
			}

			@Override
			public String getNamePrefix()
			{
				return "Profil";
			}
		}

		static class ProfilSaveLoadListener implements SaveLoadListener
		{
			@Override
			public Map<String, Object> onSave()
			{
				Map<String, Object> save= new HashMap<String, Object>( 1 );

				List<Profil> profils= Manager.instance().data;
				save.put( "profils_size", profils.size() );
				for( int i= 0; i < profils.size(); ++i ) {
					save.put( "profils_" + i, profils.get( i ).save() );
				}

				return save;
			}

			@Override
			public void onLoad( MapWithDefaults save )
			{
				List<Profil> profils= Manager.instance().data;
				int size= (int) (Integer) save.get( "profils_size", 0 );
				if( size <= 0 ) {
					Profil.Manager.instance().loadDefaults();
				} else {
					for( int i= 0; i < size; ++i ) {
						MapWithDefaults profil_data= save.getMap( "profils_" + i );
						Profil profil= Profil.load( profil_data );
						profils.add( profil );
					}
				}
			}

			@Override
			public String getNamePrefix()
			{
				return "Profil";
			}
		}
	}

	///
	/// getters
	///

	public String getNick()
	{
		return nick;
	}

	public int getLvl()
	{
		return lvl;
	}

	public int getXp()
	{
		return xp;
	}

	public List<Skill> getSkills()
	{
		return Collections.unmodifiableList( skills );
	}

	public int getLocalId()
	{
		return local_id;
	}

}
