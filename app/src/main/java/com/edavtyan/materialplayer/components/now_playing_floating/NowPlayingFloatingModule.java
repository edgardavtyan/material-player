package com.edavtyan.materialplayer.components.now_playing_floating;

import android.content.Context;

import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NowPlayingFloatingModule {
	private final Context context;
	private final NowPlayingFloatingFragment view;

	public NowPlayingFloatingModule(Context context, NowPlayingFloatingFragment view) {
		this.context = context;
		this.view = view;
	}

	@Provides
	@Singleton
	public Context provideContext() {
		return context;
	}

	@Provides
	@Singleton
	public NowPlayingFloatingFragment provideView() {
		return view;
	}

	@Provides
	@Singleton
	public NowPlayingFloatingModel provideModel(Context context, AlbumArtProvider albumArtProvider) {
		return new NowPlayingFloatingModel(context, albumArtProvider);
	}

	@Provides
	@Singleton
	public NowPlayingFloatingPresenter providePresenter(
			NowPlayingFloatingModel model,
			NowPlayingFloatingFragment view) {
		return new NowPlayingFloatingPresenter(model, view);
	}
}
