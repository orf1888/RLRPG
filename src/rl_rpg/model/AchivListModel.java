package rl_rpg.model;

public class AchivListModel
{

	private String achivName = "";
	/* Ka�dy achiv powinien mie� sw�j obrazek w zale�no�ci od grupy do kt�rej jest przydzielony */
	//private String Image = "";
	private String achivDescr = "";

	/*********** Set Methods ******************/

	public void setAchivName( String achivName )
	{
		this.achivName = achivName;
	}

	/*
	 * public void setImage( String Image ) { this.Image = Image; }
	 */

	public void setAchivDescr( String achivDescr )
	{
		this.achivDescr = achivDescr;
	}

	/*********** Get Methods ****************/

	public String getAchivName()
	{
		return this.achivName;
	}

	/*
	 * public String getImage() { return this.Image; }
	 */

	public String getAchivDescr()
	{
		return this.achivDescr;
	}
}