package com.edavtyan.materialplayer.components.audio_effects.surround;

public interface Surround {
	int getMaxStrength();
	int getStrength();
	void setStrength(int strength);
	void saveSettings();
}
