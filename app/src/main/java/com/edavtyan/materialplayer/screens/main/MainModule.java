package com.edavtyan.materialplayer.screens.main;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.screens.ActivityScope;
import com.edavtyan.materialplayer.utils.AppColors;

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
	public TextTabsAdapter provideTextTabsAdapter(FragmentManager fragmentManager) {
		return new TextTabsAdapter(fragmentManager);
	}

	@Provides
	@ActivityScope
	public IconsTabsAdapter provideIconsTabsAdapter(
			FragmentManager fragmentManager, AppCompatActivity activity, AppColors appColors) {
		return new IconsTabsAdapter(fragmentManager, activity, appColors);
	}

	@Provides
	@ActivityScope
	public FragmentManager provideFragmentManager(AppCompatActivity activity) {
		return activity.getSupportFragmentManager();
	}
}
