package com.edavtyan.materialplayer.screens.now_playing_floating;

import com.edavtyan.materialplayer.db.Track;

public class NowPlayingFloatingPresenter
		implements NowPlayingFloatingModel.OnNewTrackListener,
				   NowPlayingFloatingModel.OnServiceConnectedListener {

	private final NowPlayingFloatingModel model;
	private final NowPlayingFloatingFragment view;

	public NowPlayingFloatingPresenter(
			NowPlayingFloatingModel model,
			NowPlayingFloatingFragment view) {
		this.model = model;
		this.view = view;
	}

	public void onCreate() {
		model.bind();
		model.setOnNewTrackListener(this);
		model.setOnServiceConnectedListener(this);
	}

	public void onDestroy() {
		model.unbind();
	}

	public void onViewClick() {
		view.gotoNowPlaying();
	}

	public void onPlayPauseClick() {
		model.togglePlayPause();
		view.setIsPlaying(model.isPlaying());
	}

	@Override
	public void onNewTrack() {
		updateView();
	}

	@Override
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
