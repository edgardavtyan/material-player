package com.edavtyan.materialplayer.lib.prefs;

import android.content.SharedPreferences;

public class AdvancedSharedPrefs {
	private final SharedPreferences basePrefs;

	public AdvancedSharedPrefs(SharedPreferences basePrefs) {
		this.basePrefs = basePrefs;
	}

	public AdvancedSharedPrefsEditor edit() {
		return new AdvancedSharedPrefsEditor(basePrefs.edit());
	}

	@SuppressWarnings("unchecked")
	public <T extends Enum<T>> T getEnum(String key, T defaultEnum) {
		String enumAsString = basePrefs.getString(key, defaultEnum.name());
		return T.valueOf((Class<T>) defaultEnum.getClass(), enumAsString);
	}

	public int getInt(String key, int defValue) {
		return basePrefs.getInt(key, defValue);
	}

	public String getString(String key, String defValue) {
		return basePrefs.getString(key, defValue);
	}

	public boolean getBoolean(String key, boolean defValue) {
		return basePrefs.getBoolean(key, defValue);
	}

	public void registerOnSharedPreferenceChangeListener(
			SharedPreferences.OnSharedPreferenceChangeListener listener) {
		basePrefs.registerOnSharedPreferenceChangeListener(listener);
	}
}
