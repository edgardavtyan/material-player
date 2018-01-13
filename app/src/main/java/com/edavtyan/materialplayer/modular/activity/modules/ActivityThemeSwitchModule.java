package com.edavtyan.materialplayer.modular.activity.modules;

import android.content.SharedPreferences;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.base.BaseActivity;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.modular.activity.ActivityModule;
import com.edavtyan.materialplayer.utils.ThemeColors;

public class ActivityThemeSwitchModule
		extends ActivityModule
		implements SharedPreferences.OnSharedPreferenceChangeListener {

	private final AdvancedSharedPrefs prefs;
	private final BaseActivity activity;
	private final String themePrefKey;

	public ActivityThemeSwitchModule(BaseActivity activity, AdvancedSharedPrefs prefs) {
		this.activity = activity;
		this.prefs = prefs;
		themePrefKey = activity.getString(R.string.pref_colors_key);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(themePrefKey)) {
			callOnThemeChanged();
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		callOnThemeChanged();
	}

	private void callOnThemeChanged() {
		ThemeColors colors = new ThemeColors(activity, prefs.getInt(themePrefKey, 0));
		activity.onThemeChanged(colors);
	}
}
