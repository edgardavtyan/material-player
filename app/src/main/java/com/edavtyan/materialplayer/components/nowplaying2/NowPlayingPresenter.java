package com.edavtyan.materialplayer.components.nowplaying2;

import android.util.Log;

public class NowPlayingPresenter {

	private NowPlayingActivity2 view;
	private NowPlayingModel model;

	public void bind(NowPlayingActivity2 view, NowPlayingModel model) {
		this.view = view;
		this.model = model;
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
	}

	public void toggleShuffle() {
		model.toggleShuffle();
		view.getControlsView().setShuffling(model.isShuffling());
	}

	public void toggleRepeat() {
		model.toggleRepeat();
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

	public void rewind() {
		model.rewind();
	}

	public void playPause() {
		if (model.isPlaying()) {
			model.pause();
		} else {
			model.resume();
		}
	}

	public void fastForward() {
		model.fastForward();
	}

	public void onSeekTo(int progress) {
		model.seekTo(progress);
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
