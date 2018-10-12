package com.edavtyan.materialplayer.player;

import android.content.SharedPreferences;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import lombok.Setter;

public class PlayerPrefs implements SharedPreferences.OnSharedPreferenceChangeListener {
	private static final String PREF_SHUFFLE = "player_shuffle";
	private static final String PREF_REPEAT = "player_repeat";
	private static final String PREF_INDEX = "player_position";
	private static final String PREF_ENGINE = "player_engine";
	private static final ShuffleMode DEFAULT_SHUFFLE = ShuffleMode.DISABLED;
	private static final RepeatMode DEFAULT_REPEAT = RepeatMode.DISABLED;
	private static final int DEFAULT_INDEX = 0;
	private static final boolean DEFAULT_ENGINE = false;

	private final AdvancedSharedPrefs prefs;

	private @Setter OnUseAdvancedEngineChangedListener onUseAdvancedEngineChangedListener;

	public interface OnUseAdvancedEngineChangedListener {
		void onUseAdvancedEngineChanged(boolean isAdvanced);
	}

	public PlayerPrefs(AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
		this.prefs.registerOnSharedPreferenceChangeListener(this);
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

	public boolean getUseAdvancedEngine() {
		return prefs.getBoolean(PREF_ENGINE, DEFAULT_ENGINE);
	}

	public void saveUseAdvancedEngine(boolean use) {
		prefs.edit().putBoolean(PREF_ENGINE, use).apply();
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		switch (key) {
		case PREF_ENGINE:
			if (onUseAdvancedEngineChangedListener != null) {
				onUseAdvancedEngineChangedListener.onUseAdvancedEngineChanged(getUseAdvancedEngine());
			}
			return;
		}
	}
}
