package com.edavtyan.materialplayer.screens.main;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.screens.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
	private final AppCompatActivity activity;

	public MainModule(AppCompatActivity activity) {
		this.activity = activity;
	}

	@Provides
	@ActivityScope
	public AppCompatActivity provideActivity() {
		return activity;
	}

	@Provides
	@ActivityScope
	public TabsAdapter provideTabsAdapter(
			AppCompatActivity activity, FragmentManager fragmentManager) {
		return new TabsAdapter(activity, fragmentManager);
	}

	@Provides
	@ActivityScope
	public FragmentManager provideFragmentManager(AppCompatActivity activity) {
		return activity.getSupportFragmentManager();
	}
}
