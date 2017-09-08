package com.edavtyan.materialplayer.lib.base;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.modular.ActivityModule;
import com.edavtyan.materialplayer.utils.ThemeUtils;

public class ThemeSwitchModule
		extends ActivityModule
		implements SharedPreferences.OnSharedPreferenceChangeListener {

	private final AppCompatActivity activity;
	private final ThemeUtils themeUtils;

	public ThemeSwitchModule(
			AppCompatActivity activity,
			AdvancedSharedPrefs prefs,
			ThemeUtils themeUtils) {
		this.activity = activity;
		this.themeUtils = themeUtils;
		this.themeUtils.setTheme(activity);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		themeUtils.setThemeAndRecreate(activity, key);
	}
}
