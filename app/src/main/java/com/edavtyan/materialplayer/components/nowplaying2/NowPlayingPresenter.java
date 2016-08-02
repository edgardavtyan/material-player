package com.edavtyan.materialplayer.components.nowplaying2;

import android.util.Log;

public class NowPlayingPresenter implements NowPlayingModel.OnNewTrackListener {

	private static final int SEEK_INTERVAL = 1000;

	private NowPlayingActivity2 view;
	private NowPlayingModel model;

	private final Timer seekbarTimer = new Timer(SEEK_INTERVAL, () -> {
		view.getSeekbarView().setProgress(model.getPosition());
		view.getSeekbarView().setCurrentTime(formatTime(model.getPosition()));
	});

	public void bind(NowPlayingActivity2 view, NowPlayingModel model) {
		this.view = view;
		this.model = model;
		this.model.setOnNewTrackListener(this);
		initView();
	}

	public void initView() {
		view.getInfoView().setTitle(model.getTrackTitle());
		view.getInfoView().setInfo(model.getArtistTitle(), model.getAlbumTitle());
		view.getArtView().setArt(model.getArt());

		view.getSeekbarView().setMax(model.getDuration());
		view.getSeekbarView().setProgress(model.getPosition());
		view.getSeekbarView().setCurrentTime(formatTime(model.getPosition()));
		view.getSeekbarView().setTotalTime(formatTime(model.getDuration()));
		seekbarTimer.run();

		syncPlayPauseButton();
		syncShuffleButton();
		syncRepeatButton();
	}

	public void toggleShuffle() {
		model.toggleShuffle();
		syncShuffleButton();
	}

	public void toggleRepeat() {
		model.toggleRepeat();
		syncRepeatButton();
	}

	public void rewind() {
		model.rewind();
	}

	public void playPause() {
		if (model.isPlaying()) {
			model.pause();
			seekbarTimer.stop();
		} else {
			model.resume();
			seekbarTimer.run();
		}

		syncPlayPauseButton();
	}

	public void fastForward() {
		model.fastForward();
	}

	public void seekTo(int progress) {
		model.seekTo(progress);
	}

	public void moveSeek(int progress) {
		view.getSeekbarView().setCurrentTime(formatTime(progress));
	}

	public void openPlaylist() {
		view.openPlaylist();
	}

	@Override
	public void onNewTrack() {
		initView();
	}

	private void syncRepeatButton() {
		switch (model.getRepeatMode()) {
		case NO_REPEAT:
			view.getControlsView().setRepeatState(NowPlayingControlsView.RepeatState.NO_REPEAT);
			break;
		case REPEAT:
			view.getControlsView().setRepeatState(NowPlayingControlsView.RepeatState.REPEAT_ALL);
			break;
		case REPEAT_ONE:
			view.getControlsView().setRepeatState(NowPlayingControlsView.RepeatState.REPEAT_ONE);
			break;
		}
	}

	private void syncShuffleButton() {
		view.getControlsView().setShuffling(model.isShuffling());
	}

	private void syncPlayPauseButton() {
		view.getControlsView().setIsPlaying(model.isPlaying());
	}

	private String formatTime(int time) {
		Log.d(getClass().getSimpleName(), Integer.toString(time));
		time /= 1000;
		int seconds = time % 60;
		int minutes = time / 60;
		int hours = time / 3600;

		if (hours == 0) {
			return String.format("%d:%02d", minutes, seconds);
		} else {
			return String.format("%d:%d:%02d", hours, minutes, seconds);
		}
	}
}
