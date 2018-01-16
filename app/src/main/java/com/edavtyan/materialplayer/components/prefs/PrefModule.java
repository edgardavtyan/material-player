package com.edavtyan.materialplayer.components.prefs;

import android.app.Activity;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PrefModule {
	private final Activity activity;

	public PrefModule(Activity activity) {
		this.activity = activity;
	}

	@Provides
	@Singleton
	public Context provideContext() {
		return activity;
	}
}
