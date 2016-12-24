package com.edavtyan.materialplayer.components.player_notification;

import com.edavtyan.materialplayer.db.Track;

public class PlayerNotificationPresenter
		implements PlayerNotificationMvp.Presenter,
				   PlayerNotificationMvp.Model.OnNewTrackListener,
				   PlayerNotificationMvp.Model.OnPlayPauseListener {

	private final PlayerNotificationMvp.Model model;
	private final PlayerNotificationMvp.View view;

	public PlayerNotificationPresenter(
			PlayerNotificationMvp.Model model,
			PlayerNotificationMvp.View view) {
		this.model = model;
		this.model.setOnNewTrackListener(this);
		this.model.setOnPlayPauseListener(this);
		this.view = view;
	}

	@Override public void onCreate() {
		model.bind();
	}

	@Override public void onDestroy() {
		model.unbind();
	}

	@Override public void onNewTrack() {
		Track track = model.getTrack();
		view.setTitle(track.getTitle());
		view.setInfo(track.getArtistTitle(), track.getAlbumTitle());
		view.setIsPlaying(model.isPlaying());
		view.setArt(model.getArtPath());
		view.update();
	}

	@Override public void onPlayPause() {
		view.setIsPlaying(model.isPlaying());
		view.update();
	}
}
