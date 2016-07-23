package com.example.custompreference;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public abstract class BaseController<TPreference extends BasePreference> {
	protected final SharedPreferences sharedPrefs;
	protected final TPreference prefView;


	public BaseController(TPreference prefView) {
		sharedPrefs = PreferenceManager.getDefaultSharedPreferences(prefView.context);
		this.prefView = prefView;
	}


	public abstract CharSequence getKey();
	public abstract CharSequence getTitle();
	public abstract CharSequence getDefaultValue();


	public CharSequence getCurrentPreference() {
		return sharedPrefs.getString(getKey().toString(), getDefaultValue().toString());
	}
}
