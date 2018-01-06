package com.edavtyan.materialplayer.modular.activity.modules;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.modular.activity.ActivityModule;
import com.edavtyan.materialplayer.utils.ThemeUtils;

public class ActivityThemeSwitchModule
		extends ActivityModule
		implements SharedPreferences.OnSharedPreferenceChangeListener {

	private final AppCompatActivity activity;
	private final ThemeUtils themeUtils;

	public ActivityThemeSwitchModule(
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
