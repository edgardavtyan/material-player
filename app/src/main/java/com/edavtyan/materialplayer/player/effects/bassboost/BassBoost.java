package com.edavtyan.materialplayer.player.effects.bassboost;

public interface BassBoost {
	int getMaxStrength();
	int getStrength();
	void setStrength(int strength);
	void saveSettings();
}
