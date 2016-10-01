package com.edavtyan.materialplayer.components.audioeffects.models.equalizer;

import android.content.SharedPreferences;

import com.h6ah4i.android.media.audiofx.IEqualizer;

public class HQEqualizer implements Equalizer {
	public static final String PREF_GAINS = "pref_equalizer_gains";
	public static final String PREF_ENABLED = "pref_equalizer_enabled";


	private final IEqualizer equalizer;
	private final SharedPreferences prefs;
	private final int[] frequencies;
	private final int[] gains;


	public HQEqualizer(IEqualizer equalizer, SharedPreferences prefs) {
		this.equalizer = equalizer;
		this.prefs = prefs;

		frequencies = new int[equalizer.getNumberOfBands()];
		for (int i = 0; i < frequencies.length; i++) {
			frequencies[i] = equalizer.getCenterFreq((short) i) / 1000;
		}

		gains = new int[equalizer.getNumberOfBands()];
		String gainsStrFromPrefs = prefs.getString(PREF_GAINS, null);
		if (gainsStrFromPrefs != null) {
			String[] gainsArrayStr = gainsStrFromPrefs.split(",");
			for (int i = 0; i < gains.length; i++) {
				gains[i] = Integer.parseInt(gainsArrayStr[i]);
			}
		}

		equalizer.setEnabled(prefs.getBoolean(PREF_ENABLED, true));
	}

	/* Equalizer */

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
		return Math.abs(equalizer.getBandLevelRange()[0] / 100);
	}

	@Override
	public void setBandGain(int band, int gain) {
		equalizer.setBandLevel((short) band, (short) (gain * 100));
		gains[band] = gain;
	}

	@Override
	public void saveSettings() {
		String gainsStr = "";
		for (int i = 0; i < gains.length; i++) {
			gainsStr += gains[i];
			if (i < gains.length  -1) gainsStr += ",";
		}

		prefs.edit().putString(PREF_GAINS, gainsStr).apply();
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
