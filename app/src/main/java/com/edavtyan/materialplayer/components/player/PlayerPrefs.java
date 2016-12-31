package com.edavtyan.materialplayer.components.player;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

public class PlayerPrefs {
	private static final String PREF_SHUFFLE = "player_shuffle";
	private static final String PREF_REPEAT = "player_repeat";
	private static final ShuffleMode DEFAULT_SHUFFLE = ShuffleMode.DISABLED;
	private static final RepeatMode DEFAULT_REPEAT = RepeatMode.DISABLED;

	private final AdvancedSharedPrefs basePrefs;

	public PlayerPrefs(AdvancedSharedPrefs basePrefs) {
		this.basePrefs = basePrefs;
	}

	public ShuffleMode getShuffleMode() {
		return basePrefs.getEnum(PREF_SHUFFLE, DEFAULT_SHUFFLE);
	}

	public RepeatMode getRepeatMode() {
		return basePrefs.getEnum(PREF_REPEAT, DEFAULT_REPEAT);
	}

	public void saveShuffleMode(ShuffleMode shuffleMode) {
		basePrefs.edit().putEnum(PREF_SHUFFLE, shuffleMode).apply();
	}

	public void saveRepeatMode(RepeatMode repeatMode) {
		basePrefs.edit().putEnum(PREF_REPEAT, repeatMode).apply();
	}
}
