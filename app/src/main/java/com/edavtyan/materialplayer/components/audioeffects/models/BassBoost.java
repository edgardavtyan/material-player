package com.edavtyan.materialplayer.components.audioeffects.models;

import android.content.SharedPreferences;

import com.h6ah4i.android.media.audiofx.IBassBoost;

public class BassBoost {
	public static final String PREF_STRENGTH = "pref_bassBoost_strength";
	public static final int DEFAULT_STRENGTH = 0;
	public static final int MAX_STRENGTH = 1000;


	private IBassBoost bassBoost;
	private SharedPreferences prefs;


	public BassBoost(IBassBoost bassBoost, SharedPreferences prefs) {
		this.bassBoost = bassBoost;
		this.bassBoost.setEnabled(true);
		this.prefs = prefs;

		setStrength(prefs.getInt(PREF_STRENGTH, DEFAULT_STRENGTH));
	}


	public int getMaxStrength() {
		return MAX_STRENGTH;
	}

	public int getStrength() {
		return bassBoost.getRoundedStrength();
	}

	public void setStrength(int strength) {
		bassBoost.setStrength((short) strength);
	}

	public void saveSettings() {
		prefs.edit().putInt(PREF_STRENGTH, getStrength()).apply();
	}
}
