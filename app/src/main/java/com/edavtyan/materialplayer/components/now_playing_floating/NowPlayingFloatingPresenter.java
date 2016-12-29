package com.edavtyan.materialplayer.components.now_playing_floating;

import com.edavtyan.materialplayer.db.Track;

public class NowPlayingFloatingPresenter implements NowPlayingFloatingMvp.Presenter {
	private final NowPlayingFloatingMvp.Model model;
	private final NowPlayingFloatingMvp.View view;

	public NowPlayingFloatingPresenter(
			NowPlayingFloatingMvp.Model model,
			NowPlayingFloatingMvp.View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void onCreate() {
		model.bind();
		model.setOnNewTrackListener(this);
		model.setOnServiceConnectedListener(this);
	}

	@Override
	public void onDestroy() {
		model.unbind();
	}

	@Override
	public void onViewClick() {
		view.gotoNowPlaying();
	}

	@Override
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
