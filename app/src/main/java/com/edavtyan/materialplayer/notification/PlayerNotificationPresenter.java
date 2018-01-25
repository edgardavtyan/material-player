package com.edavtyan.materialplayer.notification;

import com.edavtyan.materialplayer.db.Track;

public class PlayerNotificationPresenter
		implements PlayerNotificationModel.OnNewTrackListener,
				   PlayerNotificationModel.OnPlayPauseListener,
				   PlayerNotificationModel.OnServiceConnectedListener {

	private final PlayerNotificationModel model;
	private final PlayerNotification view;

	public PlayerNotificationPresenter(
			PlayerNotificationModel model,
			PlayerNotification view) {
		this.model = model;
		this.model.setOnNewTrackListener(this);
		this.model.setOnPlayPauseListener(this);
		this.model.setOnServiceConnectedListener(this);
		this.view = view;
	}

	public void onCreate() {
		model.bind();
	}

	public void onDestroy() {
		model.unbind();
	}

	@Override
	public void onNewTrack() {
		Track track = model.getTrack();
		view.setTitle(track.getTitle());
		view.setInfo(track.getArtistTitle(), track.getAlbumTitle());
		view.setIsPlaying(model.isPlaying());
		view.setArt(model.getArt());
		view.update();
	}

	@Override
	public void onPlayPause() {
		view.setIsPlaying(model.isPlaying());
		view.update();
	}

	@Override
	public void onServiceConnected() {
		if (model.getTrack() != null) {
			onNewTrack();
		}
	}
}
