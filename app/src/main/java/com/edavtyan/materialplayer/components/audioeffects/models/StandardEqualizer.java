package com.edavtyan.materialplayer.components.audioeffects.models;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

public class StandardEqualizer implements Equalizer {
	public static final String PREF_GAINS = "equalizer_gains";

	private final android.media.audiofx.Equalizer equalizer;
	private final AdvancedSharedPrefs prefs;

	private final int bandsCount;
	private final int[] frequencies;
	private final int[] gains;

	public StandardEqualizer(android.media.audiofx.Equalizer equalizer, AdvancedSharedPrefs prefs) {
		this.equalizer = equalizer;
		this.prefs = prefs;

		bandsCount = equalizer.getNumberOfBands();

		frequencies = new int[bandsCount];
		for (int i = 0; i < bandsCount; i++) {
			frequencies[i] = equalizer.getCenterFreq((short) i) / 1000;
		}

		gains = prefs.getIntArray(PREF_GAINS, bandsCount);
		for (int i = 0; i < bandsCount; i++) {
			equalizer.setBandLevel((short) i, (short) gains[i]);
		}
	}

	@Override public int getBandsCount() {
		return bandsCount;
	}

	@Override public int[] getFrequencies() {
		return frequencies;
	}

	@Override public int[] getGains() {
		return gains;
	}

	@Override public void setBandGain(int band, int gain) {
		equalizer.setBandLevel((short) band, (short) gain);
		gains[band] = gain;
	}

	@Override public void saveSettings() {
		prefs.edit().putIntArray(PREF_GAINS, gains).apply();
	}

	@Override public int getGainLimit() {
		return Math.abs(equalizer.getBandLevelRange()[0] / 100);
	}

	@Override public boolean isEnabled() {
		return equalizer.getEnabled();
	}

	@Override public void setEnabled(boolean isEnabled) {
		equalizer.setEnabled(isEnabled);
	}
}
