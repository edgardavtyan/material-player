package com.edavtyan.materialplayer.lib.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AdvancedSharedPrefsModule {
	@Provides
	@Singleton
	public AdvancedSharedPrefs provideAdvancedPrefs(SharedPreferences basePrefs) {
		return new AdvancedSharedPrefs(basePrefs);
	}

	@Provides
	@Singleton
	public SharedPreferences provideBasePrefs(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
}
