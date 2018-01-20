package com.edavtyan.materialplayer.screens.prefs;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.screens.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PrefModule {
	private final AppCompatActivity activity;

	public PrefModule(AppCompatActivity activity) {
		this.activity = activity;
	}

	@Provides
	@ActivityScope
	public AppCompatActivity provideAppCompatActivity() {
		return activity;
	}
}
