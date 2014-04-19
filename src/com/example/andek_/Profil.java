package com.example.andek_;

public class Profil
{
	public String nick;
	public int lvl;
	public int xp;




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
}
