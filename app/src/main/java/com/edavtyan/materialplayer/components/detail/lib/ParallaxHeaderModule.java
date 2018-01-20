package com.edavtyan.materialplayer.components.detail.lib;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.components.ActivityScope;
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
			ParallaxHeaderListPresenter presenter) {
		return new ParallaxHeaderListCompactModule(activity, adapter, presenter);
	}
}
