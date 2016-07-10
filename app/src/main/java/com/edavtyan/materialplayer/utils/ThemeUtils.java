package com.edavtyan.materialplayer.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public final class ThemeUtils {
	private ThemeUtils() {}


	private static final String TYPE_THEME = "style";
	private static final String PREF_THEME_BASE = "pref_theme_base";
	private static final String PREF_THEME_PRIMARY = "pref_theme_primary";
	private static final String THEME_BASE = "AppTheme";
	private static final String THEME_BASE_DEFAULT = "Light";
	private static final String THEME_PRIMARY_DEFAULT = "Orange";


	public static int fromRes(Context context) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		String themeBase = prefs.getString(PREF_THEME_BASE, THEME_BASE_DEFAULT);
		String themePrimary = prefs.getString(PREF_THEME_PRIMARY, THEME_PRIMARY_DEFAULT);
		String themeName = String.format("%s.%s.%s", THEME_BASE, themeBase, themePrimary);
		return context.getResources().getIdentifier(themeName, TYPE_THEME, context.getPackageName());
	}

	public static void setTheme(Activity activity, String key) {
		boolean hasColorSchemeChanged =
				key.equals(ThemeUtils.PREF_THEME_BASE)
				|| key.equals(ThemeUtils.PREF_THEME_PRIMARY);

		if (hasColorSchemeChanged) {
			activity.setTheme(ThemeUtils.fromRes(activity));
			activity.recreate();
		}
	}
}
