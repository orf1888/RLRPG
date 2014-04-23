package rl_rpg.model;

public class Player {
	
	private String nick;
	private String lvl;
	private String XP;
	//private ArrayList<Skill>skilsList;
	//private ArrayList<Chalange> completedChalange;
	//etc...
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getLvl() {
		return lvl;
	}
	public void setLvl(String lvl) {
		this.lvl = lvl;
	}
	public String getXP() {
		return XP;
	}
	public void setXP(String xP) {
		XP = xP;
	}	
}
