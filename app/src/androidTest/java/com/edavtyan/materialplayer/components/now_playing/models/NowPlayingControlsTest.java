package com.edavtyan.materialplayer.components.now_playing.models;

import android.widget.ImageButton;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.player.RepeatMode;
import com.edavtyan.materialplayer.components.player.ShuffleMode;

import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class NowPlayingControlsTest extends NowPlayingViewTest {
	private NowPlayingMvp.View.Controls controls;
	private ImageButton shuffleButton;
	private ImageButton rewindButton;
	private ImageButton playPauseButton;
	private ImageButton fastForwardButton;
	private ImageButton repeatButton;

	@Override
	public void beforeEach() {
		super.beforeEach();

		shuffleButton = (ImageButton) activity.findViewById(R.id.shuffle);
		rewindButton = (ImageButton) activity.findViewById(R.id.rewind);
		playPauseButton = (ImageButton) activity.findViewById(R.id.play_pause);
		fastForwardButton = (ImageButton) activity.findViewById(R.id.fast_forward);
		repeatButton = (ImageButton) activity.findViewById(R.id.repeat);

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
		assertThat(shuffleButton).hasImageAlpha(255);
	}

	@Test
	public void setShuffleMode_disabled_setShuffleButtonColorFilterToContrast() {
		runOnUiThread(() -> controls.setShuffleMode(ShuffleMode.DISABLED));
		assertThat(shuffleButton).hasImageAlpha(60);
	}

	@Test
	public void setRepeatMode_repeatAll_setRepeatButtonColorFilterAndIcon() {
		runOnUiThread(() -> controls.setRepeatMode(RepeatMode.REPEAT_ALL));
		assertThat(repeatButton)
				.hasImageAlpha(255)
				.hasImageResource(R.drawable.ic_repeat);
	}

	@Test
	public void setRepeatMode_repeatOne_setRepeatButtonColorFilterAndIcon() {
		runOnUiThread(() -> controls.setRepeatMode(RepeatMode.REPEAT_ONE));
		assertThat(repeatButton)
				.hasImageAlpha(255)
				.hasImageResource(R.drawable.ic_repeat_one);
	}

	@Test
	public void setRepeatMode_disabled_setRepeatButtonColorFilterAndIcon() {
		runOnUiThread(() -> controls.setRepeatMode(RepeatMode.DISABLED));
		assertThat(repeatButton)
				.hasImageAlpha(60)
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
