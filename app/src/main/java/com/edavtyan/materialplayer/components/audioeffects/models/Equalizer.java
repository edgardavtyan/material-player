package com.edavtyan.materialplayer.components.audioeffects.models;

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
	int getCurrentBuiltInPresetIndex();
	List<String> getCustomPresetNames();
	void createNewPreset(String name);
	void deletePreset(int position);
}
