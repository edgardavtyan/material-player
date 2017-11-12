package com.edavtyan.materialplayer.lib.prefs;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.lang.reflect.Type;

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
}
