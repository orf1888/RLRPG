package rl_rpg.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import rl_rpg.utils.L;
import rl_rpg.utils.MapWithDefaults;


public class Skill implements Serializable
{
	///
	/// model
	///

	private int value;
	private SkillType type;
	private Profil parent;

	///
	/// model modifiers
	///

	public void addValue( int v )
	{
		L._assert( v > 0 && value < 1000000 );
		if( value + v < 100 )
			value+= v;
		parent.raiseSkillChanged();
	}


	///
	/// private methods
	///

	Object save()
	{
		Map<String, Object> save= new HashMap<String, Object>( 2 );
		save.put( "value", value );
		save.put( "type", type.nr );
		return save;
	}

	static Skill loadOrNull( MapWithDefaults save, Profil parent )
	{
		SkillType type= SkillType.getSkillByNr( (int) (Integer) save.get( "type", 0 ) );
		if( type == null )
			return null;

		Skill result= new Skill( type, parent );
		result.value= (int) (Integer) save.get( "value", 0 );

		// tmp
		if( type.name.equals( "Test2" ) )
			result.value= (result.value > 80) ? result.value : result.value + 15;
		// tmp
		return result;
	}

	Skill( SkillType type, Profil parent )
	{
		L._assert( type != null );
		L._assert( parent != null );
		this.value= 0;
		this.type= type;
		this.parent= parent;
	}

	/**
	public static Skill createSkill( SkillType type, Profil parent )
	{
		return new Skill( type, parent );
	}*/

	///
	/// classes
	///

	public static enum SkillType {
		Coding( 1, "Coding", "In this skill you should write a lot of lines of code" ),
		DoingNothing( 2, "DoingNothing", "Can you guess what ya have to do? Nah, ya don't have to" ),
		Test1( 3, "Test1", "Test 1" ),
		Test2( 4, "Test2", "Test 2" ), ;

		SkillType( int nr, String name, String descr )
		{
			this.nr= nr;
			this.name= name;
			this.descr= descr;
		}

		static SkillType getSkillByNr( int n )
		{
			for( SkillType s : values() )
				if( s.nr == n )
					return s;
			return null;
		}

		int nr;
		String name;
		String descr;
	}


	///
	/// getters
	///

	public int getValue()
	{
		return value;
	}

	public String getName()
	{
		return type.name;
	}

	public String getDescr()
	{
		return type.descr;
	}
}
