package com.edavtyan.materialplayer.components.audioeffects.equalizer;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

public class EqualizerPrefs {
	private static final String PREF_GAINS = "equalizer_gains";
	private static final String PREF_ENABLED = "equalizer_enabled";
	private static final boolean DEFAULT_ENABLED = false;

	private final AdvancedSharedPrefs prefs;

	public EqualizerPrefs(AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
	}

	public int[] getGains(int bandsCount) {
		return prefs.getIntArray(PREF_GAINS, bandsCount);
	}

	public boolean getEnabled() {
		return prefs.getBoolean(PREF_ENABLED, DEFAULT_ENABLED);
	}

	public void save(int[] gains, boolean enabled) {
		prefs.edit()
			 .putIntArray(PREF_GAINS, gains)
			 .putBoolean(PREF_ENABLED, enabled)
			 .apply();
	}
}
