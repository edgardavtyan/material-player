package com.edavtyan.materialplayer.ui.now_playing_bar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class NowPlayingBarDIModule {
	private final NowPlayingBarFragment view;
	private final FragmentActivity activity;

	public NowPlayingBarDIModule(FragmentActivity activity, NowPlayingBarFragment view) {
		this.activity = activity;
		this.view = view;
	}

	@Provides
	@FragmentScope
	public FragmentActivity provideActivity() {
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
			FragmentActivity activity, AlbumArtProvider albumArtProvider) {
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
