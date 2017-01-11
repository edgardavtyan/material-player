package com.edavtyan.materialplayer.components.audioeffects.models;

public class StandardBassBoost implements BassBoost {
	public static final int MAX_STRENGTH = 1000;

	private final android.media.audiofx.BassBoost bassBoost;
	private final BassBoostPrefs prefs;

	public StandardBassBoost(android.media.audiofx.BassBoost bassBoost, BassBoostPrefs prefs) {
		this.prefs = prefs;
		this.bassBoost = bassBoost;
		this.bassBoost.setEnabled(true);
		setStrength(prefs.getStrength());
	}

	@Override
	public int getMaxStrength() {
		return MAX_STRENGTH;
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
