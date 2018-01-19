package com.edavtyan.materialplayer.components.search;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {
	private final AppCompatActivity activity;

	public SearchModule(AppCompatActivity activity) {
		this.activity = activity;
	}

	@Provides
	@Singleton
	public Context provideContext() {
		return activity;
	}

	@Provides
	@Singleton
	public AppCompatActivity provideAppCompatActivity() {
		return activity;
	}
}
