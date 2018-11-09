package com.edavtyan.materialplayer.lib.theme;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.modular.universal_view.UniversalViewModule;

public class ScreenThemeModule
		extends UniversalViewModule
		implements SharedPreferences.OnSharedPreferenceChangeListener {

	private final AdvancedSharedPrefs prefs;
	private final ThemeableScreen themeableScreen;
	private final Context context;
	private final String colorsPrefKey;
	private final int defaultColor;
	private final String themePrefKey;
	private final String defaultTheme;

	public ScreenThemeModule(
			ThemeableScreen themeableScreen, Context context, AdvancedSharedPrefs prefs) {
		this.themeableScreen = themeableScreen;
		this.context = context;
		this.prefs = prefs;
		colorsPrefKey = context.getString(R.string.pref_colors_key);
		defaultColor = ContextCompat.getColor(context, R.color.pref_colors_default);
		themePrefKey = context.getString(R.string.pref_colorsMain_key);
		defaultTheme = context.getString(R.string.pref_colorsMain_defaultValue);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(colorsPrefKey) || key.equals(themePrefKey)) {
			callOnThemeChanged();

			if (themeableScreen instanceof Activity) {
				((Activity) themeableScreen).invalidateOptionsMenu();
			}
		}
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		ThemeColors colors = new ThemeColors(
				context,
				prefs.getInt(colorsPrefKey, defaultColor),
				prefs.getString(themePrefKey, defaultTheme));

		for (int i = 0; i < menu.size(); i++) {
			MenuItem item = menu.getItem(i);
			Drawable icon = item.getIcon();
			if (icon != null) {
				icon.setColorFilter(colors.getTextContrastPrimary(), PorterDuff.Mode.SRC_IN);
			}
		}

		callOnThemeChanged();
	}

	private void callOnThemeChanged() {
		ThemeColors colors = new ThemeColors(
				context,
				prefs.getInt(colorsPrefKey, defaultColor),
				prefs.getString(themePrefKey, defaultTheme));
		themeableScreen.onThemeChanged(colors);
	}
}
