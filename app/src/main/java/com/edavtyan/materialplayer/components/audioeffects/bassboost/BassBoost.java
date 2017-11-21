package com.edavtyan.materialplayer.components.audioeffects.bassboost;

public interface BassBoost {
	int getMaxStrength();
	int getStrength();
	void setStrength(int strength);
	void saveSettings();
}
