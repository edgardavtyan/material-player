package com.edavtyan.materialplayer.app.music.effects.equalizer;

public interface Equalizer {
	int getBandsCount();
	int[] getFrequencies();
	int[] getGains();
	int getBandGain(int band);
	void setBandGain(int band, int gain);
	void saveSettings();
	int getGainLimit();
	boolean isEnabled();
	void setEnabled(boolean isEnabled);
}
