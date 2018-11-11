package com.edavtyan.materialplayer.lib.theme;

import android.app.Activity;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ThemeDIModule {
	private final ThemeableScreen screen;

	public ThemeDIModule(ThemeableScreen screen) {
		this.screen = screen;
	}

	@Provides
	@Singleton
	public ThemeableScreen provideThemeableScreen() {
		return screen;
	}

	@Provides
	@Singleton
	public ScreenThemeModule provideScreenThemeModule(
			Activity activity, AdvancedSharedPrefs prefs) {
		return new ScreenThemeModule(activity, prefs);
	}
}
