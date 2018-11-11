package com.edavtyan.materialplayer.ui.now_playing_bar;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.ui.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class NowPlayingBarDIModule {
	private final NowPlayingBarFragment view;
	private final Activity activity;

	public NowPlayingBarDIModule(Activity activity, NowPlayingBarFragment view) {
		this.activity = activity;
		this.view = view;
	}

	@Provides
	@FragmentScope
	public Activity provideActivity() {
		return activity;
	}

	@Provides
	@FragmentScope
	public Fragment provideFragment() {
		return view;
	}

	@Provides
	@FragmentScope
	public NowPlayingBarFragment provideView() {
		return view;
	}

	@Provides
	@FragmentScope
	public NowPlayingBarModel provideModel(
			Activity activity, AlbumArtProvider albumArtProvider) {
		return new NowPlayingBarModel(activity, albumArtProvider);
	}

	@Provides
	@FragmentScope
	public NowPlayingBarPresenter providePresenter(
			NowPlayingBarModel model,
			NowPlayingBarFragment view) {
		return new NowPlayingBarPresenter(model, view);
	}
}
