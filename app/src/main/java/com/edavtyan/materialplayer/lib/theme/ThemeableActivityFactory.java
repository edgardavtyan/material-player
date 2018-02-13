package com.edavtyan.materialplayer.lib.theme;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.ui.ActivityScope;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import dagger.Module;
import dagger.Provides;

@Module
public class ThemeableActivityFactory {
	@Provides
	@ActivityScope
	public ScreenThemeModule provideScreenThemeModule(
			AppCompatActivity activity, Context context, AdvancedSharedPrefs prefs) {
		return new ScreenThemeModule((ThemeableScreen) activity, context, prefs);
	}
}
