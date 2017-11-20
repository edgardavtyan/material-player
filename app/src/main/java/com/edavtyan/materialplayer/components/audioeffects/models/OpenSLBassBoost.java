package com.edavtyan.materialplayer.components.audioeffects.models;

import com.h6ah4i.android.media.audiofx.IBassBoost;

public class OpenSLBassBoost implements BassBoost {
	private final IBassBoost bassBoost;
	private final BassBoostPrefs prefs;

	public OpenSLBassBoost(IBassBoost bassBoost, BassBoostPrefs prefs) {
		this.bassBoost = bassBoost;
		this.prefs = prefs;
		bassBoost.setEnabled(true);
	}

	@Override
	public int getMaxStrength() {
		return 1000;
	}

	@Override
	public int getStrength() {
		return bassBoost.getRoundedStrength();
	}

	@Override
	public void setStrength(int strength) {
		bassBoost.setStrength((short) strength);
	}

	@Override
	public void saveSettings() {
		prefs.save(getStrength());
	}
}
