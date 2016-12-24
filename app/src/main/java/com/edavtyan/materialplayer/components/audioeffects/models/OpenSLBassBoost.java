package com.edavtyan.materialplayer.components.audioeffects.models;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.h6ah4i.android.media.audiofx.IBassBoost;

public class OpenSLBassBoost implements BassBoost {
	public static final String PREF_STRENGTH = "pref_bassBoost_strength";
	public static final int DEFAULT_STRENGTH = 0;
	public static final int MAX_STRENGTH = 1000;

	private final IBassBoost bassBoost;
	private final AdvancedSharedPrefs prefs;

	public OpenSLBassBoost(IBassBoost bassBoost, AdvancedSharedPrefs prefs) {
		this.bassBoost = bassBoost;
		this.bassBoost.setEnabled(true);
		this.prefs = prefs;

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
