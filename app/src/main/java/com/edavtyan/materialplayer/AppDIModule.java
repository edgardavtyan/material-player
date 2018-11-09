package com.edavtyan.materialplayer;

import android.content.Context;

import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.ui.SdkFactory;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppDIModule {
	private final App app;

	public AppDIModule(App app) {
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
	@Singleton
	public ThemeColors provideThemeColors() {
		return new ThemeColors(app);
	}

	@Provides
	public SdkFactory provideSdkFactory() {
		return new SdkFactory();
	}
}
