package com.edavtyan.materialplayer.components.now_playing.models;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;
import com.edavtyan.materialplayer.components.player.RepeatMode;
import com.edavtyan.materialplayer.components.player.ShuffleMode;
import com.edavtyan.materialplayer.lib.testable.TestableImageButton;
import com.edavtyan.materialplayer.utils.AppColors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NowPlayingControls implements NowPlayingMvp.View.Controls {
	@BindView(R.id.shuffle) TestableImageButton shuffleButton;
	@BindView(R.id.rewind) TestableImageButton rewindButton;
	@BindView(R.id.playPause) TestableImageButton playPauseButton;
	@BindView(R.id.fastForward) TestableImageButton fastForwardButton;
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
	}

	@OnClick(R.id.shuffle)
	public void onShuffleClick() {
		presenter.onShuffleClick();
	}

	@OnClick(R.id.rewind)
	public void onRewindClick() {
		presenter.onRewindClick();
	}

	@OnClick(R.id.playPause)
	public void onPlayPauseClick() {
		presenter.onPlayPauseClick();
	}

	@OnClick(R.id.fastForward)
	public void onFastForwardClick() {
		presenter.onFastForwardClick();
	}

	@OnClick(R.id.repeat)
	public void onRepeatClick() {
		presenter.onRepeatClick();
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
