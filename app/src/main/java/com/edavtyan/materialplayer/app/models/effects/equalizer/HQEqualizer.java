package com.edavtyan.materialplayer.app.models.effects.equalizer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.h6ah4i.android.media.audiofx.IEqualizer;

public class HQEqualizer implements Equalizer {
	private static final String PREF_GAINS = "pref_equalizer_gains";
	private static final String PREF_ENABLED = "pref_equalizer_enabled";


	private final IEqualizer equalizer;
	private final Gson gson;
	private final SharedPreferences prefs;
	private final int[] frequencies;
	private final int[] gains;


	public HQEqualizer(Context context, IEqualizer equalizer) {
		this.equalizer = equalizer;
		gson = new Gson();
		prefs = PreferenceManager.getDefaultSharedPreferences(context);

		frequencies = new int[equalizer.getNumberOfBands()];
		for (int i = 0; i < frequencies.length; i++) {
			frequencies[i] = equalizer.getCenterFreq((short) i) / 1000;
		}

		int[] gainsFromPref = gson.fromJson(prefs.getString(PREF_GAINS, null), int[].class);
		gains = (gainsFromPref != null) ? gainsFromPref : new int[getBandsCount()];

		equalizer.setEnabled(prefs.getBoolean(PREF_ENABLED, true));
	}

	@Override
	public int getBandsCount() {
		return equalizer.getNumberOfBands();
	}

	@Override
	public int[] getFrequencies() {
		return frequencies;
	}

	@Override
	public int[] getGains() {
		return gains;
	}

	@Override
	public int getGainLimit() {
		return equalizer.getBandLevelRange()[1] / 100;
	}

	@Override
	public int getBandGain(int band) {
		return equalizer.getBandLevel((short) band) / 100;
	}

	@Override
	public void setBandGain(int band, int gain) {
		equalizer.setBandLevel((short) band, (short) (gain * 100));
		gains[band] = gain;
	}

	@Override
	public void saveSettings() {
		prefs.edit().putString(PREF_GAINS, gson.toJson(gains)).apply();
	}

	@Override
	public boolean isEnabled() {
		return equalizer.getEnabled();
	}

	@Override
	public void setEnabled(boolean isEnabled) {
		equalizer.setEnabled(isEnabled);
		prefs.edit().putBoolean(PREF_ENABLED, isEnabled).apply();
	}
}
