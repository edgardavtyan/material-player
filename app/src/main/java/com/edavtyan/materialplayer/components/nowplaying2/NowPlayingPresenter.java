package com.edavtyan.materialplayer.components.nowplaying2;

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
}
