package com.edavtyan.materialplayer.lib.theme;

import android.content.Context;

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
			Context context, AdvancedSharedPrefs prefs) {
		return new ScreenThemeModule(context, prefs);
	}
}
