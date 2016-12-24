package com.edavtyan.materialplayer.components.audioeffects.models;

public interface BassBoost {
	int getMaxStrength();
	int getStrength();
	void setStrength(int strength);
	void saveSettings();
}
