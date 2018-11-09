package com.edavtyan.materialplayer.lib.theme;

import android.content.Context;

import com.edavtyan.materialplayer.ui.ActivityScope;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import dagger.Module;
import dagger.Provides;

@Module
public class ThemeableActivityDIModule {
	@Provides
	@ActivityScope
	public ScreenThemeModule provideScreenThemeModule(
			Context context, AdvancedSharedPrefs prefs) {
		return new ScreenThemeModule(context, prefs);
	}
}
