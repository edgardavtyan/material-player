package com.edavtyan.prefs.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public abstract class BaseModel {
	protected final SharedPreferences sharedPrefs;

	public BaseModel(Context context) {
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public abstract String getKey();

	public abstract String getDefaultValue();

	public String getCurrentPreference() {
		return sharedPrefs.getString(getKey(), getDefaultValue());
	}
}
