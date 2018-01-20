package com.edavtyan.materialplayer.player.effects.surround;

public interface Surround {
	int getMaxStrength();
	int getStrength();
	void setStrength(int strength);
	void saveSettings();
}
