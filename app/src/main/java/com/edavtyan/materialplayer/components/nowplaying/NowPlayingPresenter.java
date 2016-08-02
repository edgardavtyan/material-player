package com.edavtyan.materialplayer.components.nowplaying;

import android.util.Log;

import com.edavtyan.materialplayer.components.nowplaying.views.NowPlayingControls;
import com.edavtyan.materialplayer.utils.Timer;

public class NowPlayingPresenter implements NowPlayingModel.OnNewTrackListener {

	private static final int SEEK_INTERVAL = 1000;

	private NowPlayingActivity view;
	private NowPlayingModel model;

	private final Timer seekbarTimer = new Timer(SEEK_INTERVAL, () -> {
		view.getSeekbar().setProgress(model.getPosition());
		view.getSeekbar().setCurrentTime(formatTime(model.getPosition()));
	});

	public void bind(NowPlayingActivity view, NowPlayingModel model) {
		this.view = view;
		this.model = model;
		this.model.setOnNewTrackListener(this);
		initView();
	}

	public void unbind() {
		this.model.setOnNewTrackListener(null);
	}

	public void initView() {
		view.getInfo().setTitle(model.getTrackTitle());
		view.getInfo().setInfo(model.getArtistTitle(), model.getAlbumTitle());
		view.getArt().setArt(model.getArt());

		view.getSeekbar().setMax(model.getDuration());
		view.getSeekbar().setProgress(model.getPosition());
		view.getSeekbar().setCurrentTime(formatTime(model.getPosition()));
		view.getSeekbar().setTotalTime(formatTime(model.getDuration()));
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
		view.getSeekbar().setCurrentTime(formatTime(progress));
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
			view.getControls().setRepeatState(NowPlayingControls.RepeatState.NO_REPEAT);
			break;
		case REPEAT:
			view.getControls().setRepeatState(NowPlayingControls.RepeatState.REPEAT_ALL);
			break;
		case REPEAT_ONE:
			view.getControls().setRepeatState(NowPlayingControls.RepeatState.REPEAT_ONE);
			break;
		}
	}

	private void syncShuffleButton() {
		view.getControls().setShuffling(model.isShuffling());
	}

	private void syncPlayPauseButton() {
		view.getControls().setIsPlaying(model.isPlaying());
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
