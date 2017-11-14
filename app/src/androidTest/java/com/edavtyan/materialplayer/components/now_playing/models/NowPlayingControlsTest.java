package com.edavtyan.materialplayer.components.now_playing.models;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;
import com.edavtyan.materialplayer.components.player.RepeatMode;
import com.edavtyan.materialplayer.components.player.ShuffleMode;
import com.edavtyan.materialplayer.lib.testable.TestableImageButton;

import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class NowPlayingControlsTest extends NowPlayingViewTest {
	private NowPlayingMvp.View.Controls controls;
	private TestableImageButton shuffleButton;
	private TestableImageButton rewindButton;
	private TestableImageButton playPauseButton;
	private TestableImageButton fastForwardButton;
	private TestableImageButton repeatButton;

	@Override
	public void beforeEach() {
		super.beforeEach();

		shuffleButton = (TestableImageButton) activity.findViewById(R.id.shuffle);
		rewindButton = (TestableImageButton) activity.findViewById(R.id.rewind);
		playPauseButton = (TestableImageButton) activity.findViewById(R.id.play_pause);
		fastForwardButton = (TestableImageButton) activity.findViewById(R.id.fast_forward);
		repeatButton = (TestableImageButton) activity.findViewById(R.id.repeat);

		controls = new NowPlayingControls(activity, presenter);
	}

	@Test
	public void shuffleButtonClicked_callPresenter() {
		runOnUiThread(() -> shuffleButton.performClick());
		verify(presenter).onShuffleClick();
	}

	@Test
	public void rewindButtonClicked_callPresenter() {
		runOnUiThread(() -> rewindButton.performClick());
		verify(presenter).onRewindClick();
	}

	@Test
	public void playPauseButtonClicked_callPresenter() {
		runOnUiThread(() -> playPauseButton.performClick());
		verify(presenter).onPlayPauseClick();
	}

	@Test
	public void fastForwardButtonClicked_callPresenter() {
		runOnUiThread(() -> fastForwardButton.performClick());
		verify(presenter).onFastForwardClick();
	}

	@Test
	public void repeatButtonClicked_callPresenter() {
		runOnUiThread(() -> repeatButton.performClick());
		verify(presenter).onRepeatClick();
	}

	@Test
	public void setShuffleMode_enabled_setShuffleButtonColorFilterToAccent() {
		runOnUiThread(() -> controls.setShuffleMode(ShuffleMode.ENABLED));
		assertThat(shuffleButton).hasColorFilter(colors.accent);
	}

	@Test
	public void setShuffleMode_disabled_setShuffleButtonColorFilterToContrast() {
		runOnUiThread(() -> controls.setShuffleMode(ShuffleMode.DISABLED));
		assertThat(shuffleButton).hasColorFilter(colors.textPrimary);
	}

	@Test
	public void setRepeatMode_repeatAll_setRepeatButtonColorFilterAndIcon() {
		runOnUiThread(() -> controls.setRepeatMode(RepeatMode.REPEAT_ALL));
		assertThat(repeatButton)
				.hasColorFilter(colors.accent)
				.hasImageResource(R.drawable.ic_repeat);
	}

	@Test
	public void setRepeatMode_repeatOne_setRepeatButtonColorFilterAndIcon() {
		runOnUiThread(() -> controls.setRepeatMode(RepeatMode.REPEAT_ONE));
		assertThat(repeatButton)
				.hasColorFilter(colors.accent)
				.hasImageResource(R.drawable.ic_repeat_one);
	}

	@Test
	public void setRepeatMode_disabled_setRepeatButtonColorFilterAndIcon() {
		runOnUiThread(() -> controls.setRepeatMode(RepeatMode.DISABLED));
		assertThat(repeatButton)
				.hasColorFilter(colors.textPrimary)
				.hasImageResource(R.drawable.ic_repeat);
	}

	@Test
	public void setPlayPauseMode_playing_setPlayPauseIconToPause() {
		runOnUiThread(() -> controls.setIsPlaying(true));
		assertThat(playPauseButton).hasImageResource(R.drawable.ic_pause);
	}

	@Test
	public void setPlayPauseMode_paused_setPlayPauseIconToPlay() {
		runOnUiThread(() -> controls.setIsPlaying(false));
		assertThat(playPauseButton).hasImageResource(R.drawable.ic_play);
	}
}
