package com.edavtyan.materialplayer.components.now_playing;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingArt;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingControls;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingFab;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingInfo;
import com.edavtyan.materialplayer.components.now_playing.models.NowPlayingSeekbar;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NowPlayingModule {
	private final NowPlayingActivity activity;

	public NowPlayingModule(NowPlayingActivity activity) {
		this.activity = activity;
	}

	@Provides
	@Singleton
	public NowPlayingActivity provideActivity() {
		return activity;
	}

	@Provides
	@Singleton
	public Context provideContext() {
		return activity;
	}

	@Provides
	@Singleton
	public AppCompatActivity provideAppCompatActivity() {
		return activity;
	}

	@Provides
	@Singleton
	public NowPlayingModel provideModel(
			NowPlayingActivity activity,
			AlbumArtProvider albumArtProvider) {
		return new NowPlayingModel(activity, albumArtProvider);
	}

	@Provides
	@Singleton
	public NowPlayingPresenter providePresenter(NowPlayingModel model, NowPlayingActivity view) {
		return new NowPlayingPresenter(model, view);
	}

	@Provides
	@Singleton
	public NowPlayingArt provideArtPartial(NowPlayingActivity activity) {
		return new NowPlayingArt(activity);
	}

	@Provides
	@Singleton
	public NowPlayingControls provideControlsPartial(
			NowPlayingActivity activity,
			NowPlayingPresenter presenter) {
		return new NowPlayingControls(activity, presenter);
	}

	@Provides
	@Singleton
	public NowPlayingFab provideFabPartial(
			NowPlayingActivity activity,
			NowPlayingPresenter presenter) {
		return new NowPlayingFab(activity, presenter);
	}

	@Provides
	@Singleton
	public NowPlayingInfo provideInfoPartial(NowPlayingActivity activity) {
		return new NowPlayingInfo(activity);
	}

	@Provides
	@Singleton
	public NowPlayingSeekbar provideSeekbarPartial(
			NowPlayingActivity activity,
			NowPlayingPresenter presenter) {
		return new NowPlayingSeekbar(activity, presenter);
	}
}
