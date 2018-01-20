package com.edavtyan.materialplayer.screens.now_playing_queue;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.screens.ActivityScope;
import com.edavtyan.materialplayer.screens.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import dagger.Module;
import dagger.Provides;

@Module
public class NowPlayingQueueFactory {
	private final NowPlayingQueueActivity activity;

	public NowPlayingQueueFactory(NowPlayingQueueActivity activity) {
		this.activity = activity;
	}

	@Provides
	@ActivityScope
	public NowPlayingQueueActivity provideView() {
		return activity;
	}

	@Provides
	@ActivityScope
	public AppCompatActivity provideAppCompatActivity() {
		return activity;
	}

	@Provides
	@ActivityScope
	public NowPlayingQueueModel provideModel(
			ModelServiceModule serviceModule,
			CompactListPref compactListPref) {
		return new NowPlayingQueueModel(serviceModule, compactListPref);
	}

	@Provides
	@ActivityScope
	public NowPlayingQueuePresenter providePresenter(
			NowPlayingQueueModel model,
			NowPlayingQueueActivity view) {
		return new NowPlayingQueuePresenter(model, view);
	}

	@Provides
	@ActivityScope
	public NowPlayingQueueAdapter provideAdapter(
			AppCompatActivity activity,
			NowPlayingQueuePresenter presenter) {
		return new NowPlayingQueueAdapter(activity, presenter);
	}
}
