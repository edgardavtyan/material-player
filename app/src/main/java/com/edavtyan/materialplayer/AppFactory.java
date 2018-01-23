package com.edavtyan.materialplayer;

import android.content.Context;

import com.edavtyan.materialplayer.screens.SdkFactory;

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
	public SdkFactory provideSdkFactory() {
		return new SdkFactory();
	}
}
