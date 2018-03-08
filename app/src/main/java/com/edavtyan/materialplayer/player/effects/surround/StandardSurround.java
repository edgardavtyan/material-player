package com.edavtyan.materialplayer.player.effects.surround;

import android.media.audiofx.Virtualizer;
import android.support.annotation.Nullable;

public class StandardSurround implements Surround {
	private final Virtualizer virtualizer;
	private final SurroundPrefs prefs;
	private final boolean isSupported;

	public StandardSurround(@Nullable Virtualizer virtualizer, SurroundPrefs prefs) {
		this.prefs = prefs;
		this.virtualizer = virtualizer;

		if (virtualizer == null) {
			isSupported = false;
			return;
		} else {
			isSupported = true;
		}

		this.virtualizer.setEnabled(true);
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
