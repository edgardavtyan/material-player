package com.edavtyan.materialplayer.components.main;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.edavtyan.materialplayer.utils.AppColors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
	private final MainActivity activity;

	public MainModule(MainActivity activity) {
		this.activity = activity;
	}

	@Provides
	@Singleton
	public MainActivity provideActivity() {
		return activity;
	}

	@Provides
	@Singleton
	public Context provideContext() {
		return activity;
	}

	@Provides
	@Singleton
	public TextTabsAdapter provideTextTabsAdapter(FragmentManager fragmentManager) {
		return new TextTabsAdapter(fragmentManager);
	}

	@Provides
	@Singleton
	public IconsTabsAdapter provideIconsTabsAdapter(
			FragmentManager fragmentManager, MainActivity activity, AppColors appColors) {
		return new IconsTabsAdapter(fragmentManager, activity, appColors);
	}

	@Provides
	@Singleton
	public FragmentManager provideFragmentManager(MainActivity activity) {
		return activity.getSupportFragmentManager();
	}
}
