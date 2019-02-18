package com.edavtyan.materialplayer.ui.now_playing_bar;

import com.edavtyan.materialplayer.db.Track;

public class NowPlayingBarPresenter {
	private final NowPlayingBarModel model;
	private final NowPlayingBarFragment view;

	public NowPlayingBarPresenter(
			NowPlayingBarModel model,
			NowPlayingBarFragment view) {
		this.model = model;
		this.view = view;
	}

	public void onCreate() {
		model.bind();
		model.setOnNewTrackListener(this::onPlayerNewTrack);
		model.setOnPlayPauseListener(this::onPlayerPlayPause);
		model.setOnServiceConnectedListener(this::onServiceConnected);
	}

	public void onDestroy() {
		model.unbind();
	}

	public void onViewClick() {
		view.gotoNowPlaying();
	}

	public void onPlayPauseButtonClick() {
		model.togglePlayPause();
		view.setIsPlaying(model.isPlaying());
	}

	public void onPlayerNewTrack() {
		updateView();
	}

	public void onPlayerPlayPause() {
		view.setIsPlaying(model.isPlaying());
	}

	public void onServiceConnected() {
		updateView();
	}

	private void updateView() {
		if (model.hasData()) {
			Track track = model.getNowPlayingTrack();
			view.setTrackTitle(track.getTitle());
			view.setTrackInfo(track.getArtistTitle(), track.getAlbumTitle());
			view.setArt(model.getNowPlayingTrackArt());
			view.setIsPlaying(model.isPlaying());
			view.setIsVisible(true);
		} else {
			view.setIsVisible(false);
		}
	}
}
