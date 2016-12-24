package com.edavtyan.materialplayer.components.audioeffects.models;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.h6ah4i.android.media.audiofx.IPreAmp;

// This class acts as a decorator for OpenSL amplifier. The OpenSL amplifier accepts float values
// from 0.0f - no volume, to 2.0f - 200% volume. This class only allows changing volume
// between 100% and 200%. It accepts integer values from 0 to 100 and converts them to float,
// where 0 is 1.0f - 100% volume, and 100 is 2.0f - 200% volume

public class OpenSLAmplifier implements Amplifier {
	public static final String PREF_STRENGTH = "pref_amplifier_strength";
	public static final float MAX_STRENGTH_FLOAT = 2.0f;
	public static final int MAX_STRENGTH = 100;
	public static final int DEFAULT_STRENGTH = 0;

	private final IPreAmp amplifier;
	private final AdvancedSharedPrefs prefs;

	public OpenSLAmplifier(IPreAmp amplifier, AdvancedSharedPrefs prefs) {
		this.amplifier = amplifier;
		this.amplifier.setEnabled(true);

		this.prefs = prefs;
		setStrength(prefs.getInt(PREF_STRENGTH, DEFAULT_STRENGTH));
	}

	@Override public int getMaxStrength() {
		return MAX_STRENGTH;
	}

	// Converts float volume to integer.
	// For example 1.4f -> 40, 1.9 -> 90
	@Override public int getStrength() {
		return Math.round((amplifier.getLevel() - 1) * MAX_STRENGTH);
	}

	// Converts integer volume to float.
	// For example 40 -> 1.4f, 90 -> 1.9f
	@Override public void setStrength(int strength) {
		float strengthFloat = (strength / (float) MAX_STRENGTH) + 1;
		if (strengthFloat > MAX_STRENGTH_FLOAT) strengthFloat = MAX_STRENGTH_FLOAT;
		amplifier.setLevel(strengthFloat);
	}

	@Override public void saveSettings() {
		prefs.edit().putInt(PREF_STRENGTH, getStrength()).apply();
	}
}
