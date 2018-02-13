package com.edavtyan.materialplayer.ui.now_playing_queue;

import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.ActivityScope;
import com.edavtyan.materialplayer.ui.SdkFactory;

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
	public NowPlayingQueueModel provideModel(ModelServiceModule serviceModule) {
		return new NowPlayingQueueModel(serviceModule);
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
			NowPlayingQueuePresenter presenter,
			SdkFactory sdkFactory) {
		return new NowPlayingQueueAdapter(activity, presenter, sdkFactory);
	}
}
