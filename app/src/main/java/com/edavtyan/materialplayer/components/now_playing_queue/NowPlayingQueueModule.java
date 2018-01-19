package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NowPlayingQueueModule {
	private final NowPlayingQueueActivity activity;

	public NowPlayingQueueModule(NowPlayingQueueActivity activity) {
		this.activity = activity;
	}

	@Provides
	@Singleton
	public Context provideContext() {
		return activity;
	}

	@Provides
	@Singleton
	public NowPlayingQueueActivity provideView() {
		return activity;
	}

	@Provides
	@Singleton
	public AppCompatActivity provideAppCompatActivity() {
		return activity;
	}

	@Provides
	@Singleton
	public NowPlayingQueueModel provideModel(
			ModelServiceModule serviceModule,
			CompactListPref compactListPref) {
		return new NowPlayingQueueModel(serviceModule, compactListPref);
	}

	@Provides
	@Singleton
	public NowPlayingQueuePresenter providePresenter(
			NowPlayingQueueModel model,
			NowPlayingQueueActivity view) {
		return new NowPlayingQueuePresenter(model, view);
	}

	@Provides
	@Singleton
	public NowPlayingQueueAdapter provideAdapter(
			Context context,
			NowPlayingQueuePresenter presenter) {
		return new NowPlayingQueueAdapter(context, presenter);
	}
}
