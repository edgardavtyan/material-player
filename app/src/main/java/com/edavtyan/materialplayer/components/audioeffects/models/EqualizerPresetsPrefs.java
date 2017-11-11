package com.edavtyan.materialplayer.components.audioeffects.models;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

public class EqualizerPresetsPrefs {
	private static final String PREF_BUILT_IN = "presets_built_in";
	private static final int DEFAULT_BUILT_IN = 3;

	private final AdvancedSharedPrefs prefs;

	public EqualizerPresetsPrefs(AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
	}

	public void saveCurrentBuiltInPreset(int index) {
		prefs.edit().putInt(PREF_BUILT_IN, index).apply();
	}

	public int getCurrentBuiltInPreset() {
		return prefs.getInt(PREF_BUILT_IN, DEFAULT_BUILT_IN);
	}
}
