package com.edavtyan.materialplayer.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public final class ThemeUtils {
    private ThemeUtils() {}


    private static final String TYPE_THEME = "style";

    public static final String PREF_THEME_BASE = "pref_theme_base";
    public static final String PREF_THEME_PRIMARY = "pref_theme_primary";
    public static final String PREF_THEME_ACCENT = "pref_theme_accent";

    public static final String THEME_BASE = "AppTheme";
    public static final String THEME_BASE_DEFAULT = "Light";
    public static final String THEME_PRIMARY_DEFAULT = "Orange";
    public static final String THEME_ACCENT_DEFAULT = "Yellow";


    public static int fromRes(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String themeBase = prefs.getString(PREF_THEME_BASE, THEME_BASE_DEFAULT);
        String themePrimary = prefs.getString(PREF_THEME_PRIMARY, THEME_PRIMARY_DEFAULT);
        String themeAccent = prefs.getString(PREF_THEME_ACCENT, THEME_ACCENT_DEFAULT);
        String themeName = String.format("%s.%s.%s.%s", THEME_BASE, themeBase, themePrimary, themeAccent);
        Log.d("ThemeUtils", themeName);
        return context.getResources().getIdentifier(themeName, TYPE_THEME, context.getPackageName());
    }
}
