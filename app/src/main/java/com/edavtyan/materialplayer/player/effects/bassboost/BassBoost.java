package com.edavtyan.materialplayer.player.effects.bassboost;

public interface BassBoost {
	boolean isSupported();
	int getMaxStrength();
	int getStrength();
	void setStrength(int strength);
	void saveSettings();
}
