package com.edavtyan.materialplayer.player.effects.equalizer;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

public class EqualizerPrefs {
	private static final String PREF_GAINS = "equalizer_gains";
	private static final String PREF_ENABLED = "equalizer_enabled";
	private static final boolean DEFAULT_ENABLED = false;

	private final AdvancedSharedPrefs prefs;

	public EqualizerPrefs(AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
	}

	public boolean getEnabled() {
		return prefs.getBoolean(PREF_ENABLED, DEFAULT_ENABLED);
	}

	public void save(boolean enabled) {
		prefs.edit().putBoolean(PREF_ENABLED, enabled).apply();
	}
}
