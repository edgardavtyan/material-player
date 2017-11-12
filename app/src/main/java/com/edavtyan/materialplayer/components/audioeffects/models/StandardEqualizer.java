package com.edavtyan.materialplayer.components.audioeffects.models;

import com.edavtyan.materialplayer.components.audioeffects.models.eq_presets.CustomPreset;
import com.edavtyan.materialplayer.components.audioeffects.models.eq_presets.PresetNameAlreadyExists;
import com.edavtyan.materialplayer.components.audioeffects.models.eq_presets.PresetsPrefs;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class StandardEqualizer implements Equalizer {
	private final android.media.audiofx.Equalizer equalizer;
	private final EqualizerPrefs prefs;
	private final PresetsPrefs presetsPrefs;

	private final @Getter int bandsCount;
	private final @Getter int[] frequencies;
	private final @Getter int[] gains;

	public StandardEqualizer(
			android.media.audiofx.Equalizer equalizer,
			EqualizerPrefs prefs,
			PresetsPrefs presetsPrefs) {
		this.equalizer = equalizer;
		this.prefs = prefs;
		this.presetsPrefs = presetsPrefs;

		setEnabled(prefs.getEnabled());

		bandsCount = equalizer.getNumberOfBands();

		frequencies = new int[bandsCount];
		for (int i = 0; i < bandsCount; i++) {
			int reverseIndex = bandsCount - i - 1;
			frequencies[i] = baseToKilo(equalizer.getCenterFreq((short) reverseIndex));
		}

		gains = prefs.getGains(bandsCount);
		for (int i = 0; i < bandsCount; i++) {
			int reverseIndex = bandsCount - i - 1;
			equalizer.setBandLevel((short) reverseIndex, (short) deciToMilli(gains[i]));
		}
	}

	@Override
	public int getGainLimit() {
		return Math.abs(milliToDeci(equalizer.getBandLevelRange()[0]));
	}

	@Override
	public void setBandGain(int band, int gain) {
		int reverseBand = bandsCount - band - 1;
		equalizer.setBandLevel((short) reverseBand, (short) deciToMilli(gain));
		gains[band] = gain;
		presetsPrefs.saveCurrentPresetType(PresetType.CUSTOM_NEW);
	}

	@Override
	public void saveSettings() {
		prefs.save(gains, isEnabled());
	}

	@Override
	public boolean isEnabled() {
		return equalizer.getEnabled();
	}

	@Override
	public void setEnabled(boolean isEnabled) {
		equalizer.setEnabled(isEnabled);
	}

	@Override
	public List<String> getBuiltInPresetNames() {
		List<String> presetNames = new ArrayList<>();
		for (short i = 0; i < equalizer.getNumberOfPresets(); i++) {
			presetNames.add(equalizer.getPresetName(i));
		}

		return presetNames;
	}

	@Override
	public List<String> getCustomPresetNames() {
		return presetsPrefs.getCustomPresets();
	}

	@Override
	public PresetType getCurrentPresetType() {
		return presetsPrefs.getCurrentPresetType();
	}

	@Override
	public int getCurrentPresetIndex() {
		return presetsPrefs.getCurrentPresetIndex();
	}

	@Override
	public boolean isUsingSavedCustomPreset() {
		return presetsPrefs.getCurrentPresetType() == PresetType.CUSTOM;
	}

	@Override
	public void useBuiltInPreset(int presetIndex) {
		equalizer.usePreset((short) presetIndex);
		presetsPrefs.saveCurrentPresetIndex(presetIndex);
		presetsPrefs.saveCurrentPresetType(PresetType.BUILT_IN);

		for (int i = 0; i < bandsCount; i++) {
			int reverseBand = bandsCount - i - 1;
			gains[i] = milliToDeci(equalizer.getBandLevel((short) reverseBand));
		}
	}

	@Override
	public void useCustomPreset(int presetIndex) {
		CustomPreset customPreset = presetsPrefs.getCustomPresetAtIndex(presetIndex);
		int[] customPresetGains = customPreset.getGains();

		for (int i = 0; i < customPresetGains.length; i++) {
			setBandGain(i, customPresetGains[i]);
		}

		presetsPrefs.saveCurrentPresetType(PresetType.CUSTOM);
		presetsPrefs.saveCurrentPresetIndex(presetIndex);
	}

	@Override
	public void savePreset(String name) throws PresetNameAlreadyExists {
		presetsPrefs.addNewCustomPreset(name, gains);
	}

	@Override
	public void deletePreset(int position) {
		presetsPrefs.deleteCustomPreset(position);
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
