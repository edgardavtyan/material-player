package com.edavtyan.materialplayer.player.effects.bassboost;

public class StandardBassBoost implements BassBoost {
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
