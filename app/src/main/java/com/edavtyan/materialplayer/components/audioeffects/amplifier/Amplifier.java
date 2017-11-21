package com.edavtyan.materialplayer.components.audioeffects.amplifier;

public interface Amplifier {
	int getGain();
	void setGain(int gain);
	int getMaxGain();
	void saveSettings();
}
