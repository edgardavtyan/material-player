package com.edavtyan.materialplayer.lib.prefs;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.List;

public class AdvancedGsonSharedPrefsEditor extends AdvancedSharedPrefsEditor {
	private final SharedPreferences.Editor baseEditor;
	private final Gson gson;

	public AdvancedGsonSharedPrefsEditor(SharedPreferences.Editor baseEditor, Gson gson) {
		super(baseEditor);
		this.baseEditor = baseEditor;
		this.gson = gson;
	}

	public <T> AdvancedGsonSharedPrefsEditor putListAsJson(String key, List<T> list) {
		String listAsJson = gson.toJson(list);
		baseEditor.putString(key, listAsJson);
		return this;
	}
}
