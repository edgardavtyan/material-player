package com.edavtyan.materialplayer.components.audioeffects.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.h6ah4i.android.media.audiofx.IPreAmp;

// This class acts as a decorator for OpenSL amplifier. The OpenSL amplifier accepts float values
// from 0.0f - no volume, to 2.0f - double volume. This class only allows changing volume
// between 100% and 200%. It accepts integer values from 0 to 100 and converts them to float,
// where 0 is 1.0f - 100% volume, and 100 is 2.0f - 200% volume

public class Amplifier {
	private static final String PREF_STRENGTH = "pref_amplifier_strength";
	private static final int MAX_STRENGTH = 100;
	private static final int DEFAULT_STRENGTH = 0;


	private final IPreAmp amplifier;
	private final SharedPreferences prefs;


	public Amplifier(Context context, IPreAmp amplifier) {
		this.amplifier = amplifier;
		this.amplifier.setEnabled(true);

		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		setStrength(prefs.getInt(PREF_STRENGTH, DEFAULT_STRENGTH));
	}


	public int getMaxStrength() {
		return MAX_STRENGTH;
	}

	// Converts float volume to integer.
	// For example 1.4f -> 40, 1.9 -> 90
	public int getStrength() {
		return (int) ((amplifier.getLevel() - 1) * MAX_STRENGTH);
	}

	// Converts integer volume to float.
	// For example 40 -> 1.4f, 90 -> 1.9f
	public void setStrength(int strength) {
		float strengthFloat = (strength / (float) MAX_STRENGTH) + 1;
		if (strengthFloat > 2.0f) strengthFloat = 2.0f;
		amplifier.setLevel(strengthFloat);
	}

	public void saveSettings() {
		prefs.edit().putInt(PREF_STRENGTH, getStrength()).apply();
	}
}
