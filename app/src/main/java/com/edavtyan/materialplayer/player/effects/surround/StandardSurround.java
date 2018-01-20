package com.edavtyan.materialplayer.player.effects.surround;

import android.media.audiofx.Virtualizer;

public class StandardSurround implements Surround {
	private final Virtualizer virtualizer;
	private final SurroundPrefs prefs;

	public StandardSurround(Virtualizer virtualizer, SurroundPrefs prefs) {
		this.prefs = prefs;
		this.virtualizer = virtualizer;
		this.virtualizer.setEnabled(true);
		setStrength(prefs.getStrength());
	}

	@Override
	public int getMaxStrength() {
		return 1000;
	}

	@Override
	public int getStrength() {
		return virtualizer.getRoundedStrength();
	}

	@Override
	public void setStrength(int strength) {
		virtualizer.setStrength((short) strength);
	}

	@Override
	public void saveSettings() {
		prefs.save(getStrength());
	}
}
