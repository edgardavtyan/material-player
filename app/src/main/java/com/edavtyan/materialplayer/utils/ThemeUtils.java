package com.edavtyan.materialplayer.utils;

import android.app.Activity;
import android.content.Context;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

public class ThemeUtils {
	private static final String TYPE_THEME = "style";
	private static final String PREF_THEME_BASE = "pref_theme_base";
	private static final String PREF_THEME_PRIMARY = "pref_theme_primary";
	private static final String THEME_BASE = "AppTheme";
	private static final String THEME_BASE_DEFAULT = "Light";
	private static final String THEME_PRIMARY_DEFAULT = "Orange";

	private AdvancedSharedPrefs prefs;

	public ThemeUtils(AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
	}

	public void setTheme(Activity activity) {
		activity.setTheme(fromRes(activity));
	}

	public void setThemeAndRecreate(Activity activity, String key) {
		if (key.equals(PREF_THEME_BASE) || key.equals(PREF_THEME_PRIMARY)) {
			activity.setTheme(fromRes(activity));
			activity.recreate();
		}
	}

	private int fromRes(Context context) {
		String themeBase = prefs.getString(PREF_THEME_BASE, THEME_BASE_DEFAULT);
		String themePrimary = prefs.getString(PREF_THEME_PRIMARY, THEME_PRIMARY_DEFAULT);
		String themeName = String.format("%s.%s.%s", THEME_BASE, themeBase, themePrimary);
		return context.getResources().getIdentifier(themeName, TYPE_THEME, context.getPackageName());
	}
}
