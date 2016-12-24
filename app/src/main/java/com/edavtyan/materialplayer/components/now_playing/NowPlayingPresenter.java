package com.edavtyan.materialplayer.components.now_playing;

import com.edavtyan.materialplayer.utils.Timer;

public class NowPlayingPresenter
		implements NowPlayingMvp.Presenter,
				   NowPlayingMvp.Model.OnNewTrackListener,
				   NowPlayingMvp.Model.OnPlayPauseListener {

	private static final int SEEK_INTERVAL = 1000;

	private final NowPlayingMvp.Model model;
	private final NowPlayingMvp.View view;

	private final Timer seekbarTimer;

	public NowPlayingPresenter(NowPlayingMvp.Model model, NowPlayingMvp.View view) {
		this.model = model;
		this.model.setOnModelBoundListener(this);
		this.model.setOnNewTrackListener(this);
		this.model.setOnPlayPauseListener(this);
		this.view = view;

		//TODO: find out how to DI this
		seekbarTimer = new Timer(SEEK_INTERVAL, () -> {
			view.getSeekbar().setTrackPosition(model.getPosition());
			view.getSeekbar().setTrackPositionText(model.getPosition());
		});
	}

	@Override
	public void bind() {
		model.bind();
	}

	@Override
	public void unbind() {
		model.unbind();
	}

	@Override
	public void onFabClick() {
		view.gotoPlaylistScreen();
	}

	@Override
	public void onModelBound() {
		view.getControls().setShuffleMode(model.getShuffleMode());
		view.getControls().setRepeatMode(model.getRepeatMode());
		view.getControls().setIsPlaying(model.isPlaying());
		updateViewInfo();
	}

	@Override
	public void onShuffleClick() {
		model.toggleShuffleMode();
		view.getControls().setShuffleMode(model.getShuffleMode());
	}

	@Override
	public void onRewindClick() {
		model.rewind();
	}

	@Override
	public void onPlayPauseClick() {
		model.playPause();
		view.getControls().setIsPlaying(model.isPlaying());

		if (model.isPlaying()) {
			seekbarTimer.run();
		} else {
			seekbarTimer.stop();
		}
	}

	@Override
	public void onFastForwardClick() {
		model.fastForward();
	}

	@Override
	public void onRepeatClick() {
		model.toggleRepeatMode();
		view.getControls().setRepeatMode(model.getRepeatMode());
	}

	@Override
	public void onTrackSeekChanged(int progress) {
		view.getSeekbar().setTrackPositionText(progress);
	}

	@Override
	public void onTrackSeekStop(int position) {
		model.seek(position);
	}

	@Override public void onNewTrack() {
		updateViewInfo();
	}

	@Override public void onPlayPause() {
		view.getControls().setIsPlaying(model.isPlaying());
	}

	private void updateViewInfo() {
		view.getInfo().setTitle(model.getTitle());
		view.getInfo().setInfo(model.getArtist(), model.getAlbum());
		view.getControls().setIsPlaying(model.isPlaying());
		view.getControls().setRepeatMode(model.getRepeatMode());
		view.getControls().setShuffleMode(model.getShuffleMode());
		view.getSeekbar().setTrackDuration(model.getDuration());
		view.getSeekbar().setTrackDurationText(model.getDuration());
		view.getArt().setArt(model.getArt());
		seekbarTimer.run();
	}
}
