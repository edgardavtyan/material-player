package com.edavtyan.materialplayer;

import android.content.Context;

import com.edavtyan.materialplayer.screens.SdkFactory;
import com.google.gson.Gson;

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

	@Provides
	@Singleton
	public Gson provideGson() {
		return new Gson();
	}

	@Provides
	public SdkFactory provideSdkFactory() {
		return new SdkFactory();
	}
}
