package com.edavtyan.materialplayer.player.effects.amplifier;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

public class AmplifierPrefs {
	private static final String GAIN_KEY = "amplifier_gain";
	private static final int GAIN_DEFAULT = 0;

	private final AdvancedSharedPrefs prefs;

	public AmplifierPrefs(AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
	}

	public int getGain() {
		return prefs.getInt(GAIN_KEY, GAIN_DEFAULT);
	}

	public void save(int gain) {
		prefs.edit().putInt(GAIN_KEY, gain).apply();
	}
}
