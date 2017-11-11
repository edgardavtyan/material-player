package com.edavtyan.materialplayer.components.audioeffects.models;

import java.util.List;

public interface Equalizer {
	int getBandsCount();
	int[] getFrequencies();
	int[] getGains();
	void setBandGain(int band, int gain);
	void saveSettings();
	int getGainLimit();
	boolean isEnabled();
	void setEnabled(boolean isEnabled);
	List<String> getBuiltInPresetNames();
}
