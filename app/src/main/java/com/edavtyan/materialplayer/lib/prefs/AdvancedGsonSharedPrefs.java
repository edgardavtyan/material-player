package com.edavtyan.materialplayer.lib.prefs;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.HashMap;

public class AdvancedGsonSharedPrefs extends AdvancedSharedPrefs {
	private final SharedPreferences basePrefs;
	private final Gson gson;

	public AdvancedGsonSharedPrefs(SharedPreferences basePrefs, Gson gson) {
		super(basePrefs);
		this.basePrefs = basePrefs;
		this.gson = gson;
	}

	@Override
	public AdvancedGsonSharedPrefsEditor edit() {
		return new AdvancedGsonSharedPrefsEditor(basePrefs.edit(), gson);
	}

	public <T> T getJsonAsList(String key, Type type, T defaultValue) {
		String jsonString = basePrefs.getString(key, null);
		T jsonList = jsonString == null
				? defaultValue
				: gson.fromJson(jsonString, type);
		return jsonList;
	}

	public void appendToMap(String prefKey, Object mapKey, Object mapValue) {
		HashMap map = gson.fromJson(basePrefs.getString(prefKey, ""), HashMap.class);

		if (map == null) {
			map = new HashMap();
		}

		map.put(mapKey, mapValue);
		basePrefs.edit().putString(prefKey, gson.toJson(map)).apply();
	}

	@Nullable
	public <T> T getValueFromMap(String prefKey, String key) {
		HashMap map = gson.fromJson(basePrefs.getString(prefKey, ""), HashMap.class);

		if (map == null) {
			return null;
		}

		return (T) gson.fromJson(basePrefs.getString(prefKey, ""), HashMap.class).get(key);
	}

	public HashMap getMap(String prefKey) {
		return gson.fromJson(basePrefs.getString(prefKey, ""), HashMap.class);
	}
}
