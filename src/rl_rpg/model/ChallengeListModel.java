package rl_rpg.model;

public class ChallengeListModel
{

	private String challengeName = "";
	/* Ka¿dy challenge powinien mieæ swój obrazek w zale¿noœci od grupy do której jest przydzielony */
	//private String Image = "";
	private String challengeDescr = "";

	/*********** Set Methods ******************/

	public void setChallengeName( String challengeName )
	{
		this.challengeName = challengeName;
	}

	/*
	 * public void setImage( String Image ) { this.Image = Image; }
	 */

	public void setChallengeDescr( String challengeDescr )
	{
		this.challengeDescr = challengeDescr;
	}

	/*********** Get Methods ****************/

	public String getChallengeName()
	{
		return this.challengeName;
	}

	/*
	 * public String getImage() { return this.Image; }
	 */

	public String getChallengeDescr()
	{
		return this.challengeDescr;
	}
}