package com.edavtyan.materialplayer.modular.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.ui.ActivityScope;
import com.edavtyan.materialplayer.ui.Navigator;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModulesDIModule {
	private final @Nullable Integer titleStringId;
	private final @Nullable Boolean isBackIconEnabled;

	public ActivityModulesDIModule() {
		this.titleStringId = null;
		this.isBackIconEnabled = null;
	}

	public ActivityModulesDIModule(@Nullable Integer titleStringId) {
		this.titleStringId = titleStringId;
		this.isBackIconEnabled = null;
	}

	public ActivityModulesDIModule(
			@Nullable Integer titleStringId,
			@Nullable Boolean isBackIconEnabled) {
		this.titleStringId = titleStringId;
		this.isBackIconEnabled = isBackIconEnabled;
	}

	@Provides
	@ActivityScope
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
	@ActivityScope
	public ActivityBaseMenuModule provideBaseMenuModule(AppCompatActivity activity, Navigator navigator) {
		return new ActivityBaseMenuModule(activity, navigator);
	}
}
