package com.example.custompreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public abstract class BaseController {
	protected final SharedPreferences prefs;


	public BaseController(Context context) {
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
	}


	public abstract CharSequence getKey();
	public abstract CharSequence getTitle();
	public abstract CharSequence getDefaultValue();


	public CharSequence getCurrentPreference() {
		return prefs.getString(getKey().toString(), getDefaultValue().toString());
	}
}
