package com.edavtyan.materialplayer.player.effects.equalizer;

import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.ui.audio_effects.presets.PresetNameAlreadyExists;

import java.util.ArrayList;
import java.util.List;

public class StandardEqualizer implements Equalizer {
	private final EqualizerBase equalizer;
	private final EqualizerPrefs prefs;
	private final PresetsPrefs presetsPrefs;

	private final boolean isSupported;

	public StandardEqualizer(
			@Nullable EqualizerBase equalizer,
			EqualizerPrefs prefs,
			PresetsPrefs presetsPrefs) {
		this.equalizer = equalizer;
		this.prefs = prefs;
		this.presetsPrefs = presetsPrefs;

		if (equalizer == null) {
			isSupported = false;
			return;
		}

		isSupported = true;

		setEnabled(prefs.getEnabled());
		equalizer.setEnabled(prefs.getEnabled());
		equalizer.setGains(prefs.getGains(equalizer.getBandsCount()));
	}

	@Override
	public int getGainLimit() {
		return equalizer.getGainLimit();
	}

	@Override
	public int getBandsCount() {
		return equalizer.getBandsCount();
	}

	@Override
	public int[] getFrequencies() {
		return equalizer.getFrequencies();
	}

	@Override
	public int[] getGains() {
		return equalizer.getGains();
	}

	@Override
	public void setBandGain(int band, int gain) {
		equalizer.setBandGain(band, gain);
		presetsPrefs.saveCurrentPresetType(PresetType.CUSTOM_NEW);
	}

	@Override
	public void saveSettings() {
		prefs.save(equalizer.getGains(), isEnabled());
	}

	@Override
	public boolean isEnabled() {
		return equalizer.isEnabled();
	}

	@Override
	public void setEnabled(boolean isEnabled) {
		equalizer.setEnabled(isEnabled);
	}

	@Override
	public List<String> getBuiltInPresetNames() {
		List<String> presetNames = new ArrayList<>();
		for (int i = 0; i < equalizer.getNumberOfPresets(); i++) {
			presetNames.add(equalizer.getPresetName(i));
		}

		return presetNames;
	}

	@Override
	public boolean isSupported() {
		return isSupported;
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
	public String getCurrentPresetName() {
		return presetsPrefs.getCustomPresetAtIndex(getCurrentPresetIndex()).getName();
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
	}

	@Override
	public void useCustomPreset(int presetIndex) {
		equalizer.setGains(presetsPrefs.getCustomPresetAtIndex(presetIndex).getGains());
		presetsPrefs.saveCurrentPresetType(PresetType.CUSTOM);
		presetsPrefs.saveCurrentPresetIndex(presetIndex);
	}

	@Override
	public void savePreset(String name) throws PresetNameAlreadyExists {
		presetsPrefs.addNewCustomPreset(name, equalizer.getGains());
	}

	@Override
	public void saveAndOverwritePreset(String name) {
		presetsPrefs.addAndOverwriteCustomPreset(name, equalizer.getGains());
	}

	@Override
	public void deletePreset(int position) {
		presetsPrefs.deleteCustomPreset(position);
	}
}
