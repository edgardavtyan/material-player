package com.edavtyan.materialplayer.components.audio_effects.bassboost;

public interface BassBoost {
	int getMaxStrength();
	int getStrength();
	void setStrength(int strength);
	void saveSettings();
}
