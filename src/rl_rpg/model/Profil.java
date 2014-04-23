package rl_rpg.model;

import java.util.ArrayList;
import java.util.Arrays;
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


public class Profil
{
	///
	/// model
	///

	private String nick;
	private int lvl;
	private int xp;
	private List<Skill> skills;

	public void addXp( int value )
	{
		L._assert( value > 0 );
		xp += value;
		this.changed = true;
	}

	///
	/// methods
	///

	/** korzystamy z tej funkcji, jesli chcemy byc powiadamiani o zmianach zachodzących w profilu,
	 *  przyklad: UI sie podpina zeby wyswietlac zmiany */
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

	/** pobiera profil osoby aktualnie korzystającej z aplikacji
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
		for (OnChangeProfilListener listener : onChangeListeners)
			listener.onChange();
	}

	///
	/// pomocnicze zmienne
	///

	ArrayList<OnChangeProfilListener> onChangeListeners = new ArrayList<Profil.OnChangeProfilListener>();
	boolean changed;

	private final String default_nick = "x1";
	private final int default_lvl = 20;
	private final int default_xp = 0;
	private final List<Skill> default_skills = new ArrayList<Skill>(
			Arrays.asList( Skill.Manager.createSkill( SkillType.DoingNothing ),
					Skill.Manager.createSkill( SkillType.Coding ),
					Skill.Manager.createSkill( SkillType.Test1 ),
					Skill.Manager.createSkill( SkillType.Test2 ),
					Skill.Manager.createSkill( SkillType.Test1 ),
					Skill.Manager.createSkill( SkillType.Test1 ),
					Skill.Manager.createSkill( SkillType.Test1 )));

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
		Map<String, Object> save = new HashMap<String, Object>( 4+skills.size() );
		save.put( "nick", nick );
		save.put( "lvl", lvl );
		save.put( "xp", xp );
		save.put( "skills_size", skills.size() );
		for(int i=0; i<skills.size(); ++i){
			save.put( "skills_"+i, skills.get( i ).save() );
		}
		return save;
	}

	void load( MapWithDefaults save )
	{
		nick = (String) save.get( "nick", default_nick );
		lvl = (int) (Integer) save.get( "lvl", default_lvl );
		xp = (int) (Integer) save.get( "xp", default_xp );
		
		int skills_count = (int) (Integer) save.get( "skills_size", 0 );
		if( skills_count > 0 ){
			skills.clear();
			for(int i=0; i<skills_count; ++i){
				Object skill_save = save.get( "skills_"+i, null );
				Skill skill = null;
				if( skill_save!=null )
					skill= Skill.loadOrNull( new MapWithDefaults( skill_save ) );
				if( skill != null )
					skills.add( skill );
			}
		}else{
			skills = (List<Skill>) ((ArrayList)default_skills).clone();
		}
		changed = true;
	}


	public static class Manager
	{
		Profil local = null;


		protected static Profil getLocalProfil()
		{
			if( instance().local == null )
				instance().local = createDefault();
			return instance().local;
		}

		static Profil createDefault()
		{
			Profil p = new Profil();
			p.load( MapWithDefaults.defaults() );
			return p;
		}

		//
		// singleton
		//

		private static final Manager INSTANCE = new Manager();

		static Manager instance()
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
				Profil local = getLocalProfil();
				if( local.changed ) {
					local.changed = false;
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
				Map<String, Object> save = new HashMap<String, Object>( 1 );
				Profil local = Manager.getLocalProfil();
				save.put( "local", local.save() );

				return save;
			}

			@Override
			public void onLoad( MapWithDefaults save )
			{
				Profil local = Manager.getLocalProfil();
				local.load( save.getMap( "local" ) );
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
		return Collections.unmodifiableList(skills);
	}

	/**
	public void setNick( String nick )
	{
		this.nick = nick;
		this.changed = true;
	}
	
	public void setLvl( int lvl )
	{
		this.lvl = lvl;
		this.changed = true;
	}

	public void setXp( int xp )
	{
		this.xp = xp;
		this.changed = true;
	}
	*/

}
