package com.edavtyan.materialplayer.components.now_playing.models;

import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;
import com.edavtyan.materialplayer.components.player2.RepeatMode;
import com.edavtyan.materialplayer.components.player2.ShuffleMode;
import com.edavtyan.materialplayer.lib.testable.TestableImageButton;
import com.edavtyan.materialplayer.utils.AppColors;

public class NowPlayingControls implements NowPlayingMvp.View.Controls, View.OnClickListener {
	private final NowPlayingMvp.Presenter presenter;
	private final AppColors colors;

	private final TestableImageButton shuffleButton;
	private final TestableImageButton playPauseButton;
	private final TestableImageButton repeatButton;

	public NowPlayingControls(
			NowPlayingActivity activity,
			NowPlayingMvp.Presenter presenter,
			AppColors colors) {
		this.presenter = presenter;
		this.colors = colors;

		shuffleButton = activity.findView(R.id.shuffle);
		shuffleButton.setOnClickListener(this);

		TestableImageButton rewindButton = activity.findView(R.id.rewind);
		rewindButton.setOnClickListener(this);

		playPauseButton = activity.findView(R.id.playPause);
		playPauseButton.setOnClickListener(this);

		TestableImageButton fastForwardButton = activity.findView(R.id.fastForward);
		fastForwardButton.setOnClickListener(this);

		repeatButton = activity.findView(R.id.repeat);
		repeatButton.setOnClickListener(this);
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
		case R.id.playPause:
			presenter.onPlayPauseClick();
			break;
		case R.id.fastForward:
			presenter.onFastForwardClick();
			break;
		case R.id.repeat:
			presenter.onRepeatClick();
			break;
		}
	}

	@Override
	public void setShuffleMode(ShuffleMode shuffleMode) {
		switch (shuffleMode) {
		case ENABLED:
			shuffleButton.setColorFilterNonFinal(colors.accent);
			break;
		case DISABLED:
			shuffleButton.setColorFilterNonFinal(colors.textPrimary);
			break;
		}
	}

	@Override
	public void setRepeatMode(RepeatMode repeatMode) {
		switch (repeatMode) {
		case REPEAT_ALL:
			repeatButton.setColorFilterNonFinal(colors.accent);
			repeatButton.setImageResource(R.drawable.ic_repeat);
			break;
		case REPEAT_ONE:
			repeatButton.setColorFilterNonFinal(colors.accent);
			repeatButton.setImageResource(R.drawable.ic_repeat_one);
			break;
		case DISABLED:
			repeatButton.setColorFilterNonFinal(colors.textPrimary);
			repeatButton.setImageResource(R.drawable.ic_repeat);
			break;
		}
	}

	@Override
	public void setIsPlaying(boolean isPlaying) {
		playPauseButton.setImageResource(isPlaying ? R.drawable.ic_pause : R.drawable.ic_play);
	}
}
