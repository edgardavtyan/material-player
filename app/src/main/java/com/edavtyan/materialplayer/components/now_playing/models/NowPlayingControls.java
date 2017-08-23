package com.edavtyan.materialplayer.components.now_playing.models;

import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;
import com.edavtyan.materialplayer.components.player.RepeatMode;
import com.edavtyan.materialplayer.components.player.ShuffleMode;
import com.edavtyan.materialplayer.lib.testable.TestableImageButton;
import com.edavtyan.materialplayer.utils.AppColors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NowPlayingControls implements NowPlayingMvp.View.Controls, View.OnClickListener {
	@BindView(R.id.shuffle) TestableImageButton shuffleButton;
	@BindView(R.id.rewind) TestableImageButton rewindButton;
	@BindView(R.id.play_pause) TestableImageButton playPauseButton;
	@BindView(R.id.fast_forward) TestableImageButton fastForwardButton;
	@BindView(R.id.repeat) TestableImageButton repeatButton;

	private final NowPlayingMvp.Presenter presenter;
	private final AppColors colors;

	public NowPlayingControls(
			NowPlayingActivity activity,
			NowPlayingMvp.Presenter presenter,
			AppColors colors) {
		ButterKnife.bind(this, activity);
		this.presenter = presenter;
		this.colors = colors;

		shuffleButton.setOnClickListener(this);
		rewindButton.setOnClickListener(this);
		playPauseButton.setOnClickListener(this);
		fastForwardButton.setOnClickListener(this);
		repeatButton.setOnClickListener(this);
	}

	@Override
	public void setShuffleMode(ShuffleMode shuffleMode) {
		switch (shuffleMode) {
		case ENABLED:
			shuffleButton.setAlpha(1f);
			break;
		case DISABLED:
			shuffleButton.setAlpha(0.5f);
			break;
		}
	}

	@Override
	public void setRepeatMode(RepeatMode repeatMode) {
		switch (repeatMode) {
		case REPEAT_ALL:
			repeatButton.setImageResource(R.drawable.ic_repeat);
			repeatButton.setAlpha(1f);
			break;
		case REPEAT_ONE:
			repeatButton.setImageResource(R.drawable.ic_repeat_one);
			repeatButton.setAlpha(1f);
			break;
		case DISABLED:
			repeatButton.setImageResource(R.drawable.ic_repeat);
			repeatButton.setAlpha(0.5f);
			break;
		}
	}

	@Override
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
