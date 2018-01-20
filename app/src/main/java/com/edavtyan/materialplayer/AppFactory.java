package com.edavtyan.materialplayer;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppFactory {
	private final App app;

	public AppFactory(App app) {
		this.app = app;
	}

	@Provides
	@Singleton
	public Context provideContext() {
		return app;
	}
}
