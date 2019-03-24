package com.edavtyan.materialplayer.player;

import android.content.SharedPreferences;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import java.util.HashMap;

public class PlayerPrefs implements SharedPreferences.OnSharedPreferenceChangeListener {
	public static final String PREF_SHUFFLE = "player_shuffle";
	public static final String PREF_REPEAT = "player_repeat";
	public static final String PREF_INDEX = "player_position";
	public static final String PREF_ENGINE = "player_engine";
	public static final ShuffleMode DEFAULT_SHUFFLE = ShuffleMode.DISABLED;
	public static final RepeatMode DEFAULT_REPEAT = RepeatMode.DISABLED;
	public static final int DEFAULT_INDEX = 0;
	public static final boolean DEFAULT_ENGINE = false;

	private final AdvancedSharedPrefs prefs;

	private final HashMap<Object, OnUseAdvancedEngineChangedListener> onUseAdvancedEngineChangedListeners;

	public interface OnUseAdvancedEngineChangedListener {
		void onUseAdvancedEngineChanged(boolean isAdvanced);
	}

	public PlayerPrefs(AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
		this.prefs.registerOnSharedPreferenceChangeListener(this);

		onUseAdvancedEngineChangedListeners = new HashMap<>();
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

	public void addOnUseAdvancedEngineListener(Object owner, OnUseAdvancedEngineChangedListener listener) {
		onUseAdvancedEngineChangedListeners.put(owner, listener);
	}

	public void removeOnUseAdvancedEngineListener(Object owner) {
		onUseAdvancedEngineChangedListeners.remove(owner);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		switch (key) {
		case PREF_ENGINE:
			for (OnUseAdvancedEngineChangedListener listener : onUseAdvancedEngineChangedListeners.values()) {
				listener.onUseAdvancedEngineChanged(getUseAdvancedEngine());
			}
			return;
		}
	}
}
