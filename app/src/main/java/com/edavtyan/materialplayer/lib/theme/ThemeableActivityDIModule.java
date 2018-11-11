package com.edavtyan.materialplayer.lib.theme;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.ui.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ThemeableActivityDIModule {
	@Provides
	@ActivityScope
	public ScreenThemeModule provideScreenThemeModule(
			AppCompatActivity activity, AdvancedSharedPrefs prefs) {
		return new ScreenThemeModule(activity, prefs);
	}

	@Provides
	@ActivityScope
	public ThemeColors provideThemeColors(Activity activity) {
		return new ThemeColors(activity);
	}
}
