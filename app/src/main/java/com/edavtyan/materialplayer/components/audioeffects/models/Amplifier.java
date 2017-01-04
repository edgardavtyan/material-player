package com.edavtyan.materialplayer.components.audioeffects.models;

public interface Amplifier {
	int getGain();
	void setGain(int gain);
	int getMaxGain();
	void saveSettings();
}
