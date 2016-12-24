package com.edavtyan.materialplayer.components.audioeffects.models;

import android.media.audiofx.Virtualizer;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

public class StandardSurround implements Surround {
	private static final String PREF_STRENGTH = "surround_strength";
	private static final int DEFAULT_STRENGTH = 0;

	private final Virtualizer virtualizer;
	private final AdvancedSharedPrefs prefs;

	public StandardSurround(Virtualizer virtualizer, AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
		this.virtualizer = virtualizer;
		this.virtualizer.setEnabled(true);
		setStrength(prefs.getInt(PREF_STRENGTH, DEFAULT_STRENGTH));
	}

	@Override public int getMaxStrength() {
		return 1000;
	}

	@Override public int getStrength() {
		return virtualizer.getRoundedStrength();
	}

	@Override public void setStrength(int strength) {
		virtualizer.setStrength((short) strength);
	}

	@Override public void saveSettings() {
		prefs.edit().putInt(PREF_STRENGTH, getStrength()).apply();
	}
}
