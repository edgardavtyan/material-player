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

	public int[] getIntArray(String key, int arraySize) {
		String arrayAsString = basePrefs.getString(key, null);

		if (arrayAsString == null) return new int[arraySize];

		String[] elementsAsString = arrayAsString.split(",");
		int[] elements = new int[elementsAsString.length];

		try {
			for (int i = 0; i < elements.length; i++) {
				elements[i] = Integer.parseInt(elementsAsString[i]);
			}
		} catch (NumberFormatException e) {
			return new int[arraySize];
		}

		return elements;
	}
}
