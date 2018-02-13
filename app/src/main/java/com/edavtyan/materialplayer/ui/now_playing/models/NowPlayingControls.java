package com.edavtyan.materialplayer.ui.now_playing.models;

import android.view.View;
import android.widget.ImageButton;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.ui.now_playing.NowPlayingPresenter;
import com.edavtyan.materialplayer.player.RepeatMode;
import com.edavtyan.materialplayer.player.ShuffleMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NowPlayingControls implements View.OnClickListener {
	public static final int CONTROL_ENABLED_ALPHA = 255;
	public static final int CONTROL_DISABLED_ALPHA = 60;

	@BindView(R.id.shuffle) ImageButton shuffleButton;
	@BindView(R.id.rewind) ImageButton rewindButton;
	@BindView(R.id.play_pause) ImageButton playPauseButton;
	@BindView(R.id.fast_forward) ImageButton fastForwardButton;
	@BindView(R.id.repeat) ImageButton repeatButton;

	private final NowPlayingPresenter presenter;

	public NowPlayingControls(
			NowPlayingActivity activity,
			NowPlayingPresenter presenter) {
		ButterKnife.bind(this, activity);
		this.presenter = presenter;

		shuffleButton.setOnClickListener(this);
		rewindButton.setOnClickListener(this);
		playPauseButton.setOnClickListener(this);
		fastForwardButton.setOnClickListener(this);
		repeatButton.setOnClickListener(this);
	}

	public void setShuffleMode(ShuffleMode shuffleMode) {
		switch (shuffleMode) {
		case ENABLED:
			shuffleButton.setImageAlpha(CONTROL_ENABLED_ALPHA);
			break;
		case DISABLED:
			shuffleButton.setImageAlpha(CONTROL_DISABLED_ALPHA);
			break;
		}
	}

	public void setRepeatMode(RepeatMode repeatMode) {
		switch (repeatMode) {
		case REPEAT_ALL:
			repeatButton.setImageResource(R.drawable.ic_repeat);
			repeatButton.setImageAlpha(CONTROL_ENABLED_ALPHA);
			break;
		case REPEAT_ONE:
			repeatButton.setImageResource(R.drawable.ic_repeat_one);
			repeatButton.setImageAlpha(CONTROL_ENABLED_ALPHA);
			break;
		case DISABLED:
			repeatButton.setImageResource(R.drawable.ic_repeat);
			repeatButton.setImageAlpha(CONTROL_DISABLED_ALPHA);
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
			presenter.onShuffleClick();
			break;
		case R.id.rewind:
			presenter.onRewindClick();
			break;
		case R.id.play_pause:
			presenter.onPlayPauseClick();
			break;
		case R.id.fast_forward:
			presenter.onFastForwardClick();
			break;
		case R.id.repeat:
			presenter.onRepeatClick();
			break;
		}
	}
}
