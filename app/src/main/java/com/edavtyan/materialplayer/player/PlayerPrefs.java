package com.edavtyan.materialplayer.player;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

public class PlayerPrefs {
	private static final String PREF_SHUFFLE = "player_shuffle";
	private static final String PREF_REPEAT = "player_repeat";
	private static final String PREF_INDEX = "player_position";
	private static final ShuffleMode DEFAULT_SHUFFLE = ShuffleMode.DISABLED;
	private static final RepeatMode DEFAULT_REPEAT = RepeatMode.DISABLED;
	private static final int DEFAULT_INDEX = 0;

	private final AdvancedSharedPrefs prefs;

	public PlayerPrefs(AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
	}

	public ShuffleMode getShuffleMode() {
		return prefs.getEnum(PREF_SHUFFLE, DEFAULT_SHUFFLE);
	}

	public void saveShuffleMode(ShuffleMode shuffleMode) {
		prefs.edit().putEnum(PREF_SHUFFLE, shuffleMode).apply();
	}

	public RepeatMode getRepeatMode() {
		return prefs.getEnum(PREF_REPEAT, DEFAULT_REPEAT);
	}

	public void saveRepeatMode(RepeatMode repeatMode) {
		prefs.edit().putEnum(PREF_REPEAT, repeatMode).apply();
	}

	public int getCurrentIndex() {
		return prefs.getInt(PREF_INDEX, DEFAULT_INDEX);
	}

	public void saveCurrentIndex(int position) {
		prefs.edit().putInt(PREF_INDEX, position).apply();
	}
}
