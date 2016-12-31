package com.edavtyan.materialplayer.components.audioeffects.models;

public class StandardEqualizer implements Equalizer {
	private final android.media.audiofx.Equalizer equalizer;
	private final EqualizerPrefs prefs;

	private final int bandsCount;
	private final int[] frequencies;
	private final int[] gains;

	public StandardEqualizer(android.media.audiofx.Equalizer equalizer, EqualizerPrefs prefs) {
		this.equalizer = equalizer;
		this.prefs = prefs;

		setEnabled(prefs.getEnabled());

		bandsCount = equalizer.getNumberOfBands();

		frequencies = new int[bandsCount];
		for (int i = 0; i < bandsCount; i++) {
			int reverseIndex = bandsCount - i - 1;
			frequencies[i] = equalizer.getCenterFreq((short) reverseIndex) / 1000;
		}

		gains = prefs.getGains(bandsCount);
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
		int reverseBand = bandsCount - band - 1;
		equalizer.setBandLevel((short) reverseBand, (short) (gain * 100));
		gains[band] = gain;
	}

	@Override public void saveSettings() {
		prefs.save(gains, isEnabled());
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
