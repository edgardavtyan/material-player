package com.edavtyan.materialplayer.lib.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public abstract class BooleanPref implements SharedPreferences.OnSharedPreferenceChangeListener {
	private final AdvancedSharedPrefs prefs;
	private final String key;
	private final boolean defaultValue;
	private boolean value;

	public BooleanPref(Context context, AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
		this.prefs.registerOnSharedPreferenceChangeListener(this);

		key = context.getString(getKeyId());
		defaultValue = context.getResources().getBoolean(getDefaultValueId());
		value = prefs.getBoolean(key, defaultValue);
	}

	public boolean getValue() {
		return value;
	}

	protected abstract int getKeyId();
	protected abstract int getDefaultValueId();

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (this.key.equals(key)) {
			value = prefs.getBoolean(key, defaultValue);
		}
	}
}
