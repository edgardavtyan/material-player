package com.edavtyan.materialplayer.components.nowplaying.views;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.nowplaying.NowPlayingPresenter;
import com.edavtyan.materialplayer.utils.AppColors;

public class NowPlayingControls implements View.OnClickListener {
	private final NowPlayingPresenter presenter;
	private final AppColors colors;

	private final ImageView shuffleButton;
	private final ImageView rewindButton;
	private final ImageView playPauseButton;
	private final ImageView fastForwardButton;
	private final ImageView repeatButton;

	public enum RepeatState {
		NO_REPEAT, REPEAT_ONE, REPEAT_ALL
	}

	public NowPlayingControls(Activity view, NowPlayingPresenter presenter) {
		this.presenter = presenter;
		this.colors = new AppColors(view);

		shuffleButton = (ImageView) view.findViewById(R.id.shuffle);
		shuffleButton.setOnClickListener(this);
		rewindButton = (ImageView) view.findViewById(R.id.rewind);
		rewindButton.setOnClickListener(this);
		playPauseButton = (ImageView) view.findViewById(R.id.playPause);
		playPauseButton.setOnClickListener(this);
		fastForwardButton = (ImageView) view.findViewById(R.id.fastForward);
		fastForwardButton.setOnClickListener(this);
		repeatButton = (ImageView) view.findViewById(R.id.repeat);
		repeatButton.setOnClickListener(this);
	}

	public void setShuffling(boolean shuffling) {
		shuffleButton.setColorFilter(shuffling ? colors.accent : colors.textPrimary);
	}

	public void setRepeatState(RepeatState state) {
		switch (state) {
		case NO_REPEAT:
			repeatButton.setImageResource(R.drawable.ic_repeat);
			repeatButton.setColorFilter(colors.textPrimary);
			break;
		case REPEAT_ONE:
			repeatButton.setImageResource(R.drawable.ic_repeat_one);
			repeatButton.setColorFilter(colors.accent);
			break;
		case REPEAT_ALL:
			repeatButton.setImageResource(R.drawable.ic_repeat);
			repeatButton.setColorFilter(colors.accent);
			break;
		}
	}

	public void setIsPlaying(boolean isPlaying) {
		playPauseButton.setImageResource(isPlaying ? R.drawable.ic_pause : R.drawable.ic_play);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.shuffle:
			presenter.toggleShuffle();
			break;
		case R.id.rewind:
			presenter.rewind();
			break;
		case R.id.playPause:
			presenter.playPause();
			break;
		case R.id.fastForward:
			presenter.fastForward();
			break;
		case R.id.repeat:
			presenter.toggleRepeat();
			break;
		}
	}
}
