package com.edavtyan.materialplayer.lib.prefs;

import android.content.SharedPreferences;

public class AdvancedSharedPrefsEditor {
	private final SharedPreferences.Editor baseEditor;

	public AdvancedSharedPrefsEditor(SharedPreferences.Editor baseEditor) {
		this.baseEditor = baseEditor;
	}

	public void apply() {
		baseEditor.apply();
	}

	public void commit() {
		baseEditor.commit();
	}

	public AdvancedSharedPrefsEditor clear() {
		baseEditor.clear();
		return this;
	}

	public AdvancedSharedPrefsEditor putInt(String key, int defValue) {
		baseEditor.putInt(key, defValue);
		return this;
	}

	public AdvancedSharedPrefsEditor putString(String key, String defValue) {
		baseEditor.putString(key, defValue);
		return this;
	}

	public AdvancedSharedPrefsEditor putBoolean(String key, boolean defValue) {
		baseEditor.putBoolean(key, defValue);
		return this;
	}
}
