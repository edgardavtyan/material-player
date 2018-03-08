package com.edavtyan.materialplayer.player.effects.surround;

public interface Surround {
	boolean isSupported();
	int getMaxStrength();
	int getStrength();
	void setStrength(int strength);
	void saveSettings();
}
