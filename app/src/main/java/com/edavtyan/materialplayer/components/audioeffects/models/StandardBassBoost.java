package com.edavtyan.materialplayer.components.audioeffects.models;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

public class StandardBassBoost implements BassBoost {
	public static final int MAX_STRENGTH = 1000;
	public static final String PREF_STRENGTH = "bassBoost_strength";
	public static final int DEFAULT_STRENGTH = 0;

	private final android.media.audiofx.BassBoost bassBoost;
	private final AdvancedSharedPrefs prefs;

	public StandardBassBoost(android.media.audiofx.BassBoost bassBoost, AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
		this.bassBoost = bassBoost;
		this.bassBoost.setEnabled(true);
		setStrength(prefs.getInt(PREF_STRENGTH, DEFAULT_STRENGTH));
	}

	@Override public int getMaxStrength() {
		return MAX_STRENGTH;
	}

	@Override public int getStrength() {
		return bassBoost.getRoundedStrength();
	}

	@Override public void setStrength(int strength) {
		bassBoost.setStrength((short) strength);
	}

	@Override public void saveSettings() {
		prefs.edit().putInt(PREF_STRENGTH, getStrength()).apply();
	}
}
