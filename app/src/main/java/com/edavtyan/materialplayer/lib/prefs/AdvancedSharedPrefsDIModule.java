package com.edavtyan.materialplayer.lib.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AdvancedSharedPrefsDIModule {
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

	@Provides
	@Singleton
	public AdvancedGsonSharedPrefs provideAdvancedGsonPrefs(SharedPreferences basePrefs, Gson gson) {
		return new AdvancedGsonSharedPrefs(basePrefs, gson);
	}
}
