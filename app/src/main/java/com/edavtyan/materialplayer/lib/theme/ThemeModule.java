package com.edavtyan.materialplayer.lib.theme;

import android.content.Context;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ThemeModule {
	private final ThemeModularScreen screen;

	public ThemeModule(ThemeModularScreen screen) {
		this.screen = screen;
	}

	@Provides
	@Singleton
	public ThemeModularScreen provideThemeModularScreen() {
		return screen;
	}

	@Provides
	@Singleton
	public ThemeSwitchModule provideThemeSwitchModule(
			ThemeModularScreen modularScreen, Context context, AdvancedSharedPrefs prefs) {
		return new ThemeSwitchModule(modularScreen, context, prefs);
	}
}
