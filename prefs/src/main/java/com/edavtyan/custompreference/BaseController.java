package com.edavtyan.custompreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public abstract class BaseController {
	protected final SharedPreferences sharedPrefs;


	public BaseController(Context context) {
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
	}


	public abstract CharSequence getKey();
	public abstract CharSequence getTitle();
	public abstract CharSequence getDefaultValue();


	public CharSequence getCurrentPreference() {
		return sharedPrefs.getString(getKey().toString(), getDefaultValue().toString());
	}
}
