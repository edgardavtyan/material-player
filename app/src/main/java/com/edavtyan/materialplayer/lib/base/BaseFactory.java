package com.edavtyan.materialplayer.lib.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

public class BaseFactory {
	private final Context context;
	private Navigator navigator;
	private AdvancedSharedPrefs prefs;
	private SharedPreferences basePrefs;

	public BaseFactory(Context context) {
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

	public Navigator getNavigator() {
		if (navigator == null) navigator = new Navigator(context);
		return navigator;
	}

	public AdvancedSharedPrefs getPrefs() {
		if (prefs == null)
			prefs = new AdvancedSharedPrefs(getBasePrefs());
		return prefs;
	}

	public SharedPreferences getBasePrefs() {
		if (basePrefs == null)
			basePrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
		return basePrefs;
	}
}
