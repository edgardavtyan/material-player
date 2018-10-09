package com.edavtyan.materialplayer.ui.detail.lib;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.ui.ActivityScope;
import com.edavtyan.materialplayer.lib.transition.SharedTransitionsManager;

import dagger.Module;
import dagger.Provides;

@Module
public class ParallaxHeaderDIModule {
	@Provides
	@ActivityScope
	public ParallaxHeaderListModule provideParallaxHeaderListModule(
			AppCompatActivity activity,
			TestableRecyclerAdapter adapter,
			ParallaxHeaderListPresenter presenter,
			SharedTransitionsManager transitionsManager) {
		return new ParallaxHeaderListModule(activity, adapter, presenter, transitionsManager);
	}
}
