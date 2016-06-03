package com.edavtyan.materialplayer.app.models.effects;

public interface StrengthBasedEffect {
	int getMaxStrength();
	int getStrength();
	void setStrength(int strength);
	void saveSettings();
}
