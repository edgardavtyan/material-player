package com.edavtyan.materialplayer.player.effects.bassboost;

import android.support.annotation.Nullable;

public class StandardBassBoost implements BassBoost {
	private final android.media.audiofx.BassBoost bassBoost;
	private final BassBoostPrefs prefs;
	private final boolean isSupported;

	public StandardBassBoost(
			@Nullable android.media.audiofx.BassBoost bassBoost, BassBoostPrefs prefs) {
		this.prefs = prefs;
		this.bassBoost = bassBoost;

		if (bassBoost == null) {
			isSupported = false;
			return;
		} else {
			isSupported = true;
		}

		this.bassBoost.setEnabled(true);
		setStrength(prefs.getStrength());
	}

	@Override
	public boolean isSupported() {
		return isSupported;
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
