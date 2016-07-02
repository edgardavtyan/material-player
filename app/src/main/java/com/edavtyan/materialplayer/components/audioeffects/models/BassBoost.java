package com.edavtyan.materialplayer.components.audioeffects.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.h6ah4i.android.media.audiofx.IBassBoost;

public class BassBoost implements StrengthBasedEffect {
	private static final String PREF_STRENGTH = "pref_bassBoost_strength";
	private static final int DEFAULT_STRENGTH = 0;
	private static final int MAX_STRENGTH = 1000;


	private IBassBoost bassBoost;
	private SharedPreferences prefs;


	public BassBoost(Context context, IBassBoost bassBoost) {
		this.bassBoost = bassBoost;
		this.bassBoost.setEnabled(true);

		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		setStrength(prefs.getInt(PREF_STRENGTH, DEFAULT_STRENGTH));
	}

	/* StrengthBasedEffect */

	@Override
	public int getMaxStrength() {
		return MAX_STRENGTH;
	}

	@Override
	public int getStrength() {
		return bassBoost.getRoundedStrength();
	}

	@Override
	public void setStrength(int strength) {
		bassBoost.setStrength((short) strength);
	}

	@Override
	public void saveSettings() {
		prefs.edit().putInt(PREF_STRENGTH, getStrength()).apply();
	}
}
