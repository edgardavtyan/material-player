package com.edavtyan.materialplayer.lib.theme;

import android.content.Context;
import android.content.SharedPreferences;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.modular.universal_view.UniversalViewModule;
import com.jakewharton.processphoenix.ProcessPhoenix;

public class ScreenThemeModule
		extends UniversalViewModule
		implements SharedPreferences.OnSharedPreferenceChangeListener {

	private final Context context;
	private final String colorsPrefKey;
	private final String themePrefKey;

	public ScreenThemeModule(Context context, AdvancedSharedPrefs prefs) {
		this.context = context;
		colorsPrefKey = context.getString(R.string.pref_colors_key);
		themePrefKey = context.getString(R.string.pref_colorsMain_key);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(colorsPrefKey) || key.equals(themePrefKey)) {
			ProcessPhoenix.triggerRebirth(context);
		}
	}
}
