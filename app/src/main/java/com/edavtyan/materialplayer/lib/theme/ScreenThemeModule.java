package com.edavtyan.materialplayer.lib.theme;

import android.app.Activity;
import android.content.SharedPreferences;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.modular.universal_view.UniversalViewModule;

public class ScreenThemeModule
		extends UniversalViewModule
		implements SharedPreferences.OnSharedPreferenceChangeListener {

	private final Activity activity;
	private final String colorsPrefKey;
	private final String themePrefKey;

	public ScreenThemeModule(Activity activity, AdvancedSharedPrefs prefs) {
		this.activity = activity;
		colorsPrefKey = this.activity.getString(R.string.pref_colors_key);
		themePrefKey = this.activity.getString(R.string.pref_colorsMain_key);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(colorsPrefKey) || key.equals(themePrefKey)) {
			activity.recreate();
		}
	}
}
