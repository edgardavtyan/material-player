package com.edavtyan.materialplayer.modular.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.components.Navigator;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModulesModule {
	private final AppCompatActivity activity;
	private final @Nullable Integer titleStringId;
	private final @Nullable Boolean isBackIconEnabled;

	public ActivityModulesModule(AppCompatActivity activity) {
		this.activity = activity;
		this.titleStringId = null;
		this.isBackIconEnabled = null;
	}

	public ActivityModulesModule(AppCompatActivity activity, @Nullable Integer titleStringId) {
		this.activity = activity;
		this.titleStringId = titleStringId;
		this.isBackIconEnabled = null;
	}

	public ActivityModulesModule(
			AppCompatActivity activity,
			@Nullable Integer titleStringId,
			@Nullable Boolean isBackIconEnabled) {
		this.activity = activity;
		this.titleStringId = titleStringId;
		this.isBackIconEnabled = isBackIconEnabled;
	}

	@Provides
	@Singleton
	public AppCompatActivity provideActivity() {
		return activity;
	}

	@Provides
	@Singleton
	public ActivityToolbarModule provideActivityToolbarModule(AppCompatActivity activity) {
		if (titleStringId != null && isBackIconEnabled != null) {
			return new ActivityToolbarModule(activity, titleStringId, isBackIconEnabled);
		} else if (titleStringId != null && isBackIconEnabled == null) {
			return new ActivityToolbarModule(activity, titleStringId);
		} else {
			return new ActivityToolbarModule(activity);
		}
	}

	@Provides
	@Singleton
	public ActivityBaseMenuModule provideBaseMenuModule(AppCompatActivity activity, Navigator navigator) {
		return new ActivityBaseMenuModule(activity, navigator);
	}
}
