package com.edavtyan.materialplayer.player.effects.equalizer;

import com.h6ah4i.android.media.audiofx.IEqualizer;

import lombok.Getter;

public class OpenSLEqualizerBase implements EqualizerBase {
	private final IEqualizer equalizer;

	private final @Getter int bandsCount;
	private final @Getter int[] frequencies;

	private @Getter int[] gains;

	public OpenSLEqualizerBase(IEqualizer equalizer) {
		bandsCount = equalizer.getNumberOfBands();
		this.equalizer = equalizer;

		frequencies = new int[bandsCount];
		for (int i = 0; i < bandsCount; i++) {
			int reverseIndex = bandsCount - i - 1;
			frequencies[i] = baseToKilo(equalizer.getCenterFreq((short) reverseIndex));
		}
	}

	@Override
	public boolean hasControl() {
		return equalizer.hasControl();
	}

	@Override
	public int getGainLimit() {
		return Math.abs(milliToDeci(equalizer.getBandLevelRange()[0]));
	}

	@Override
	public void setGains(int[] gains) {
		this.gains = gains;
		for (int i = 0; i < bandsCount; i++) {
			int reverseIndex = bandsCount - i - 1;
			equalizer.setBandLevel((short) reverseIndex, (short) deciToMilli(gains[i]));
		}
	}

	@Override
	public void setBandGain(int band, int gain) {
		int reverseBand = bandsCount - band - 1;
		equalizer.setBandLevel((short) reverseBand, (short) deciToMilli(gain));
		gains[band] = gain;
	}

	@Override
	public boolean isEnabled() {
		return equalizer.getEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		equalizer.setEnabled(enabled);
	}

	@Override
	public int getNumberOfPresets() {
		return equalizer.getNumberOfPresets();
	}

	@Override
	public String getPresetName(int index) {
		return equalizer.getPresetName((short) index);
	}

	@Override
	public void usePreset(int presetIndex) {
		equalizer.usePreset((short) presetIndex);
		for (int i = 0; i < bandsCount; i++) {
			int reverseBand = bandsCount - i - 1;
			gains[i] = milliToDeci(equalizer.getBandLevel((short) reverseBand));
		}
	}

	private int baseToKilo(int value) {
		return value / 1000;
	}

	private int milliToDeci(int value) {
		return value / 100;
	}

	private int deciToMilli(int value) {
		return value * 100;
	}
}