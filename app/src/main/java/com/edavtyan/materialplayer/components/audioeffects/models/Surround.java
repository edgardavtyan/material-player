package com.edavtyan.materialplayer.components.audioeffects.models;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.h6ah4i.android.media.audiofx.IVirtualizer;

public class Surround {
	public static final String PREF_STRENGTH = "pref_surround_strength";
	public static final int MAX_STRENGTH = 1000;
	public static final int DEFAULT_STRENGTH = 0;

	private final IVirtualizer surround;
	private final AdvancedSharedPrefs prefs;

	public Surround(IVirtualizer surround, AdvancedSharedPrefs prefs) {
		this.surround = surround;
		this.surround.setEnabled(true);
		this.prefs = prefs;

		setStrength(prefs.getInt(PREF_STRENGTH, DEFAULT_STRENGTH));
	}

	public int getMaxStrength() {
		return MAX_STRENGTH;
	}

	public int getStrength() {
		return surround.getRoundedStrength();
	}

	public void setStrength(int strength) {
		surround.setStrength((short) strength);
	}

	public void saveSettings() {
		prefs.edit().putInt(PREF_STRENGTH, getStrength()).apply();
	}
}
