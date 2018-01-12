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

	public ActivityThemeSwitchModule(BaseActivity activity, AdvancedSharedPrefs prefs) {
		this.activity = activity;
		this.prefs = prefs;
		prefs.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(activity.getString(R.string.pref_colors_key))) {
			ThemeColors colors = new ThemeColors(activity, prefs.getInt(key, 0));
			activity.recreate();
			activity.onThemeChanged(colors);
		}
	}
}
