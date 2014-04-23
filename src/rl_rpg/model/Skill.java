package rl_rpg.model;

public class Skill
{
	private Skill( String skillName, String skillDescr )
	{
		this.skillName = skillName;
		this.skillValue = 0;
		this.skillDescr = skillDescr;
	}

	///
	/// model
	///

	private String skillName;
	private int skillValue;
	private String skillDescr;

	public enum SkillType {
		Coding("Coding", "In this skill you should write a lot of lines of code"),
		DoingNothing("DoingNothing", "Can you guess what ya have to do? Nah, ya don't have to"),
		Test1("Test1", "Test 1"),
		Test2("Test2", "Test 2"),
		;
		
		SkillType( String name, String descr ){
			this.name= name;
			this.descr= descr;
		}
		
		String name;
		String descr;
	}

	//
	//
	//

	public static class Manager
	{
		public static Skill createSkill( SkillType type )
		{
			return new Skill( type.name, type.descr );
		}
	}

	/* Getters */
	public String getSkillDescr()
	{
		return skillDescr;
	}

	public String getSkillName()
	{
		return skillName;
	}

	public int getSkillValue()
	{
		return skillValue;
	}

	/* Setters */
	public void setSkillDescr( String skillDescr )
	{
		this.skillDescr = skillDescr;
	}

	public void setSkillName( String skillName )
	{
		this.skillName = skillName;
	}

	public void setSkillValue( int skillValue )
	{
		this.skillValue = skillValue;
	}

}
