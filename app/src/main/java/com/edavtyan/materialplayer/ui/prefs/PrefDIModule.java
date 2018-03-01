package com.edavtyan.materialplayer.ui.prefs;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.ui.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PrefDIModule {
	private final AppCompatActivity activity;

	public PrefDIModule(AppCompatActivity activity) {
		this.activity = activity;
	}

	@Provides
	@ActivityScope
	public AppCompatActivity provideAppCompatActivity() {
		return activity;
	}
}
