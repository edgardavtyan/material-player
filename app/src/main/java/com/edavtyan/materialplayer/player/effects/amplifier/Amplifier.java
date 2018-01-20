package com.edavtyan.materialplayer.player.effects.amplifier;

public interface Amplifier {
	int getGain();
	void setGain(int gain);
	int getMaxGain();
	void saveSettings();
}
