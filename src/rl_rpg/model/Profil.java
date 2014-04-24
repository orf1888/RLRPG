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

public class Profil implements Serializable {
	private static final long serialVersionUID = 1L;

	// /
	// / model
	// /

	private String nick;
	private int lvl;
	private int xp;
	private List<Skill> skills;

	// /
	// / model modifiers
	// /

	public void addXp(int value) {
		L._assert(value > 0 && value < 1000000);
		xp += value;
		this.changed = true;
	}

	// /
	// / methods
	// /

	/**
	 * korzystamy z tej funkcji, jesli chcemy byc powiadamiani o zmianach
	 * zachodz¹cych w profilu, przyklad: UI sie podpina zeby wyswietlac zmiany
	 * 
	 * @param string
	 */
	public void addOnChangeListener(String name, OnChangeProfilListener listener) {
		onChangeListeners.put(name, listener);
	}

	public void removeOnChangeListener(String name) {
		onChangeListeners.remove(name);
	}

	/**
	 * pobiera profil osoby aktualnie korzystaj¹cej z aplikacji
	 */
	public static Profil getLocal() {
		return Manager.getLocalProfil();
	}

	// /
	// / private methods
	// /

	private void raiseOnChanged() {
		for (OnChangeProfilListener listener : onChangeListeners.values())
			listener.onChange();
	}

	void raiseSkillChanged() {
		raiseOnChanged();
	}

	Object save() {
		Map<String, Object> save = new HashMap<String, Object>(
				4 + skills.size());
		save.put("nick", nick);
		save.put("lvl", lvl);
		save.put("xp", xp);
		save.put("skills_size", skills.size());
		for (int i = 0; i < skills.size(); ++i) {
			save.put("skills_" + i, skills.get(i).save());
		}
		return save;
	}

	void load(MapWithDefaults save) {
		nick = (String) save.get("nick", default_nick);
		lvl = (int) (Integer) save.get("lvl", default_lvl);
		xp = (int) (Integer) save.get("xp", default_xp);

		int skills_count = (int) (Integer) save.get("skills_size", 0);
		if (skills_count > 0) {
			skills.clear();
			for (int i = 0; i < skills_count; ++i) {
				Object skill_save = save.get("skills_" + i, null);
				Skill skill = null;
				if (skill_save != null)
					skill = Skill.loadOrNull(new MapWithDefaults(skill_save),
							this);
				if (skill != null)
					skills.add(skill);
			}
		} else {
			skills = default_skills(this);
		}
		changed = true;
	}

	// /
	// / pomocnicze zmienne
	// /

	Map<String, OnChangeProfilListener> onChangeListeners = new HashMap<String, Profil.OnChangeProfilListener>();
	boolean changed;

	private final String default_nick = "x1";
	private final int default_lvl = 20;
	private final int default_xp = 0;

	private final ArrayList<Skill> default_skills(Profil parent) {
		ArrayList<Skill> result = new ArrayList<Skill>();
		result.add(new Skill(SkillType.DoingNothing, this));
		result.add(new Skill(SkillType.Coding, this));
		result.add(new Skill(SkillType.Test1, this));
		result.add(new Skill(SkillType.Test2, this));
		result.add(new Skill(SkillType.Test1, this));
		result.add(new Skill(SkillType.Test1, this));
		result.add(new Skill(SkillType.Test1, this));
		return result;
	}

	/** pomocnicze */
	Profil() {
	}

	/** pomocnicze */
	Profil(String nick, int lvl, int xp, /** !!! */
	boolean toJestPomocniczyKonstruktor) {
		this.nick = nick;
		this.lvl = lvl;
		this.xp = xp;
	}

	// /
	// / classes
	// /

	public static class _ProfilDelta {

	}

	public static interface OnChangeProfilListener {
		public void onChange();
	}

	public static class Manager {
		Profil local = null;// createDefault();

		protected static Profil getLocalProfil() {
			// if( instance().local == null )
			// instance().local = createDefault();
			return instance().local;
		}

		public void init() {
			local = new Profil();
			local.load(MapWithDefaults.defaults());

			//
			{
				tmp1 = new Profil("zdzisiek", 17, 1, true);
				tmp2 = new Profil("franek", 3, 1, true);
				tmp3 = new Profil("kapitan dupa", 9000, 1, true);
				ArrayList<Skill> skills1 = new ArrayList<Skill>();
				skills1.add(new Skill(SkillType.DoingNothing, tmp1));
				skills1.add(new Skill(SkillType.Coding, tmp1));
				tmp1.skills = skills1;
				ArrayList<Skill> skills2 = new ArrayList<Skill>();
				skills2.add(new Skill(SkillType.DoingNothing, tmp2));
				skills2.add(new Skill(SkillType.Coding, tmp2));
				tmp2.skills = skills2;
				ArrayList<Skill> skills3 = new ArrayList<Skill>();
				skills3.add(new Skill(SkillType.DoingNothing, tmp3));
				skills3.add(new Skill(SkillType.Coding, tmp3));
				tmp3.skills = skills3;
			}//
		}

		Profil tmp1, tmp2, tmp3;

		public static Profil getProfilById(long id) {
			if (id == 1)
				return instance().tmp1;
			else if (id == 2)
				return instance().tmp2;
			else
				return instance().tmp3;
		}

		//
		// singleton
		//

		private static final Manager INSTANCE = new Manager();

		public static Manager instance() {
			return INSTANCE;
		}

		private Manager() {
			RLRPGApplication.addSaveLoadListener(new ProfilSaveLoadListener());
			RLRPGApplication.addLoopListener(new ProfilLoopListener());
		}

		//
		// listenery
		//

		static class ProfilLoopListener implements LoopListener {
			@Override
			public void onTick() {
				Profil local = getLocalProfil();
				if (local.changed) {
					local.changed = false;
					local.raiseOnChanged();
				}
			}

			@Override
			public String getNamePrefix() {
				return "Profil";
			}
		}

		static class ProfilSaveLoadListener implements SaveLoadListener {
			@Override
			public Map<String, Object> onSave() {
				Map<String, Object> save = new HashMap<String, Object>(1);
				Profil local = Manager.getLocalProfil();
				save.put("local", local.save());

				return save;
			}

			@Override
			public void onLoad(MapWithDefaults save) {
				Profil local = Manager.getLocalProfil();
				local.load(save.getMap("local"));
				// /
				++local.lvl;
				// /
			}

			@Override
			public String getNamePrefix() {
				return "Profil";
			}
		}
	}

	// /
	// / getters
	// /

	public String getNick() {
		return nick;
	}

	public int getLvl() {
		return lvl;
	}

	public int getXp() {
		return xp;
	}

	public List<Skill> getSkills() {
		return Collections.unmodifiableList(skills);
	}

}
