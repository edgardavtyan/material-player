package com.edavtyan.materialplayer.ui.now_playing;

import com.ed.libsutils.utils.Timer;

public class NowPlayingPresenter
		implements NowPlayingModel.OnModelBoundListener,
				   NowPlayingModel.OnNewTrackListener,
				   NowPlayingModel.OnPlayPauseListener {

	private static final int SEEK_INTERVAL = 1000;

	private final NowPlayingModel model;
	private final NowPlayingActivity view;

	private final Timer seekbarTimer;

	public NowPlayingPresenter(NowPlayingModel model, NowPlayingActivity view) {
		this.model = model;
		this.model.setOnModelBoundListener(this);
		this.model.setOnNewTrackListener(this);
		this.model.setOnPlayPauseListener(this);
		this.view = view;

		//TODO: find out how to DI this
		seekbarTimer = new Timer(SEEK_INTERVAL, () -> {
			view.getSeekbar().setPosition(model.getPosition());
			view.getSeekbar().setPositionText(model.getPosition());
		});
	}

	public void bind() {
		model.bind();
	}

	public void unbind() {
		model.unbind();
	}

	public void onFabClick() {
		view.gotoPlaylistScreen();
	}

	public void onShuffleClick() {
		model.toggleShuffleMode();
		view.getControls().setShuffleMode(model.getShuffleMode());
	}

	public void onRewindClick() {
		model.rewind();
	}

	public void onPlayPauseClick() {
		model.playPause();
		view.getControls().setIsPlaying(model.isPlaying());

		if (model.isPlaying()) {
			seekbarTimer.run();
		} else {
			seekbarTimer.stop();
		}
	}

	public void onFastForwardClick() {
		model.fastForward();
	}

	public void onRepeatClick() {
		model.toggleRepeatMode();
		view.getControls().setRepeatMode(model.getRepeatMode());
	}

	public void onSeekChanged(int position) {
		view.getSeekbar().setPositionText(position);
	}

	public void onSeekStop(int position) {
		model.seek(position);
	}

	@Override
	public void onModelBound() {
		updateViewInfo();
	}

	@Override
	public void onNewTrack() {
		updateViewInfo();
	}

	@Override
	public void onPlayPause() {
		view.getControls().setIsPlaying(model.isPlaying());
	}

	private void updateViewInfo() {
		view.getInfo().setTitle(model.getTitle());
		view.getInfo().setInfo(model.getArtist(), model.getAlbum());
		view.getSeekbar().setDuration(model.getDuration());
		view.getSeekbar().setDurationText(model.getDuration());
		view.getArt().setArt(model.getArt());
		view.getControls().setShuffleMode(model.getShuffleMode());
		view.getControls().setRepeatMode(model.getRepeatMode());
		view.getControls().setIsPlaying(model.isPlaying());
		seekbarTimer.run();
	}
}
