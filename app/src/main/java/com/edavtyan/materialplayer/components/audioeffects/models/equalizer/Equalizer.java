package com.edavtyan.materialplayer.components.audioeffects.models.equalizer;

public interface Equalizer {
	int getBandsCount();
	int[] getFrequencies();
	int[] getGains();
	void setBandGain(int band, int gain);
	void saveSettings();
	int getGainLimit();
	boolean isEnabled();
	void setEnabled(boolean isEnabled);
}
