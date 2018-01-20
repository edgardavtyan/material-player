package com.edavtyan.materialplayer.components.prefs;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.components.ActivityScope;

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
