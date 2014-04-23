package rl_rpg.model;

public class SkillListModel
{
	private String skillName = "";
	/* Tu powinni�my stworzy� osobn� klas� odpowiedzialna za lvl skilla (ile xp jest ile xp do nast�pnego lvl itp...) */
	private int skillValue;
	private String skillDescr = "";

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
