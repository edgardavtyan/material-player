package com.edavtyan.materialplayer.screens.detail.lib;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.CurrentSharedViews;
import com.edavtyan.materialplayer.screens.ActivityScope;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class ParallaxHeaderModule {
	@Provides
	@ActivityScope
	public ParallaxHeaderListModule provideParallaxHeaderListModule(
			AppCompatActivity activity,
			TestableRecyclerAdapter adapter,
			ParallaxHeaderListPresenter presenter) {
		return new ParallaxHeaderListModule(activity, adapter, presenter);
	}

	@Provides
	@ActivityScope
	public ParallaxHeaderListCompactModule provideParallaxHeaderListCompactModule(
			AppCompatActivity activity,
			TestableRecyclerAdapter adapter,
			ParallaxHeaderListPresenter presenter,
			CurrentSharedViews currentSharedViews) {
		return new ParallaxHeaderListCompactModule(activity, adapter, presenter, currentSharedViews);
	}
}
