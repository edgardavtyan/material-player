package com.edavtyan.materialplayer.lib.theme;

import android.content.Context;

import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import dagger.Module;
import dagger.Provides;

@Module
public class ThemeableFragmentDIModule {
	@Provides
	@FragmentScope
	public ScreenThemeModule provideScreenThemeModule(
			Context context, AdvancedSharedPrefs prefs) {
		return new ScreenThemeModule(context, prefs);
	}
}
