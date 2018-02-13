package com.edavtyan.materialplayer.screens.detail.lib;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.screens.ActivityScope;
import com.edavtyan.materialplayer.transition.SharedTransitionsManager;

import dagger.Module;
import dagger.Provides;

@Module
public class ParallaxHeaderModule {
	@Provides
	@ActivityScope
	public ParallaxHeaderListModule provideParallaxHeaderListCompactModule(
			AppCompatActivity activity,
			TestableRecyclerAdapter adapter,
			ParallaxHeaderListPresenter presenter,
			SharedTransitionsManager transitionsManager) {
		return new ParallaxHeaderListModule(activity, adapter, presenter, transitionsManager);
	}
}
