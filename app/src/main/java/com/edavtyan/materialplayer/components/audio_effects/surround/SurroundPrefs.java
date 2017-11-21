package com.edavtyan.materialplayer.components.audio_effects.surround;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

public class SurroundPrefs {
	private static final String PREF_STRENGTH = "surround_strength";
	private static final int DEFAULT_STRENGTH = 0;

	private final AdvancedSharedPrefs basePrefs;

	public SurroundPrefs(AdvancedSharedPrefs basePrefs) {
		this.basePrefs = basePrefs;
	}

	public int getStrength() {
		return basePrefs.getInt(PREF_STRENGTH, DEFAULT_STRENGTH);
	}

	public void save(int strength) {
		basePrefs.edit().putInt(PREF_STRENGTH, strength).commit();
	}
}
