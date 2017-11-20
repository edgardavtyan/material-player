package com.edavtyan.materialplayer.lib.prefs;

import android.content.SharedPreferences;

import com.edavtyan.materialplayer.utils.Strings;

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

	public AdvancedSharedPrefsEditor putInt(String key, int defValue) {
		baseEditor.putInt(key, defValue);
		return this;
	}

	public AdvancedSharedPrefsEditor putBoolean(String key, boolean defValue) {
		baseEditor.putBoolean(key, defValue);
		return this;
	}

	public AdvancedSharedPrefsEditor putIntArray(String key, int[] value) {
		String arrayAsString = Strings.join(value, ",");
		baseEditor.putString(key, arrayAsString);
		return this;
	}

	public <T extends Enum<T>> AdvancedSharedPrefsEditor putEnum(String key, T value) {
		baseEditor.putString(key, value == null ? null : value.name());
		return this;
	}
}
