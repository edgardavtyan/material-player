package com.edavtyan.materialplayer.ui.main;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.ui.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainDIModule {
	private final AppCompatActivity activity;

	public MainDIModule(AppCompatActivity activity) {
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