package com.edavtyan.materialplayer.components.audio_effects.amplifier;

public interface Amplifier {
	int getGain();
	void setGain(int gain);
	int getMaxGain();
	void saveSettings();
}
