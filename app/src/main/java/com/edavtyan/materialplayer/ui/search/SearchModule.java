package com.edavtyan.materialplayer.ui.search;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.ui.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {
	private final AppCompatActivity activity;

	public SearchModule(AppCompatActivity activity) {
		this.activity = activity;
	}

	@Provides
	@ActivityScope
	public AppCompatActivity provideAppCompatActivity() {
		return activity;
	}
}
