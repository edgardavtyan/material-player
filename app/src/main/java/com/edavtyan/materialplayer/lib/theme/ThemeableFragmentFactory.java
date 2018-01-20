package com.edavtyan.materialplayer.lib.theme;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ThemeableFragmentFactory {
	@Provides
	@Singleton
	public ScreenThemeModule provideScreenThemeModule(
			Fragment fragment, Context context, AdvancedSharedPrefs prefs) {
		return new ScreenThemeModule((ThemeableScreen) fragment, context, prefs);
	}
}
