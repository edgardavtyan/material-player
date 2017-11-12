package com.edavtyan.materialplayer.components.audioeffects.models;

import com.edavtyan.materialplayer.components.audioeffects.models.eq_presets.PresetNameAlreadyExists;

import java.util.List;

public interface Equalizer {
	enum PresetType {
		BUILT_IN, CUSTOM, CUSTOM_NEW
	}

	int getBandsCount();
	int[] getFrequencies();
	int[] getGains();
	void setBandGain(int band, int gain);
	void saveSettings();
	int getGainLimit();
	boolean isEnabled();
	void setEnabled(boolean isEnabled);
	List<String> getBuiltInPresetNames();
	void useBuiltInPreset(int presetIndex);
	void useCustomPreset(int presetIndex);
	int getCurrentPresetIndex();
	List<String> getCustomPresetNames();
	void savePreset(String name) throws PresetNameAlreadyExists;
	void deletePreset(int position);
	boolean isUsingSavedCustomPreset();
	PresetType getCurrentPresetType();
}
