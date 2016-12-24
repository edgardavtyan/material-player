package com.edavtyan.materialplayer.components.audioeffects.models;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.h6ah4i.android.media.audiofx.IVirtualizer;

public class OpenSLSurround implements Surround {
	public static final String PREF_STRENGTH = "pref_surround_strength";
	public static final int MAX_STRENGTH = 1000;
	public static final int DEFAULT_STRENGTH = 0;

	private final IVirtualizer surround;
	private final AdvancedSharedPrefs prefs;

	public OpenSLSurround(IVirtualizer surround, AdvancedSharedPrefs prefs) {
		this.surround = surround;
		this.surround.setEnabled(true);
		this.prefs = prefs;

		setStrength(prefs.getInt(PREF_STRENGTH, DEFAULT_STRENGTH));
	}

	@Override public int getMaxStrength() {
		return MAX_STRENGTH;
	}

	@Override public int getStrength() {
		return surround.getRoundedStrength();
	}

	@Override public void setStrength(int strength) {
		surround.setStrength((short) strength);
	}

	@Override public void saveSettings() {
		prefs.edit().putInt(PREF_STRENGTH, getStrength()).apply();
	}
}
