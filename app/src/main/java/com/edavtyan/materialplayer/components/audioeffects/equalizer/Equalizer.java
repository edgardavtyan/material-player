package com.edavtyan.materialplayer.components.audioeffects.equalizer;

import com.edavtyan.materialplayer.components.audioeffects.equalizer.presets.PresetNameAlreadyExists;

import java.util.List;

public interface Equalizer {
	enum PresetType {
		BUILT_IN, CUSTOM, CUSTOM_NEW
	}

	int getGainLimit();
	int getBandsCount();
	int[] getFrequencies();
	int[] getGains();
	void setBandGain(int band, int gain);
	void saveSettings();
	boolean isEnabled();
	void setEnabled(boolean isEnabled);
	List<String> getBuiltInPresetNames();
	List<String> getCustomPresetNames();
	PresetType getCurrentPresetType();
	int getCurrentPresetIndex();
	boolean isUsingSavedCustomPreset();
	void useBuiltInPreset(int presetIndex);
	void useCustomPreset(int presetIndex);
	void savePreset(String name) throws PresetNameAlreadyExists;
	void deletePreset(int position);
}
