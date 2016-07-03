package com.edavtyan.materialplayer.components.audioeffects.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.h6ah4i.android.media.audiofx.IVirtualizer;

public class Surround {
	private static final String PREF_ENABLED = "pref_surround_enabled";
	private static final String PREF_STRENGTH = "pref_surround_strength";
	private static final int MAX_STRENGTH = 1000;


	private final IVirtualizer surround;
	private final SharedPreferences prefs;


	public Surround(Context context, IVirtualizer surround) {
		this.surround = surround;
		this.surround.setEnabled(true);
		prefs = PreferenceManager.getDefaultSharedPreferences(context);

		setStrength(prefs.getInt(PREF_STRENGTH, 0));
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
