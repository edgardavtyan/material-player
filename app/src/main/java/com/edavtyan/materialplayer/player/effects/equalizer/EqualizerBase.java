package com.edavtyan.materialplayer.player.effects.equalizer;

public interface EqualizerBase {
	boolean hasControl();
	int getBandsCount();
	int getGainLimit();
	int[] getFrequencies();
	int[] getGains();
	void setGains(int[] gains);
	void setBandGain(int band, int gain);
	boolean isEnabled();
	void setEnabled(boolean enabled);
	int getNumberOfPresets();
	String getPresetName(int index);
	void usePreset(int presetIndex);
}
