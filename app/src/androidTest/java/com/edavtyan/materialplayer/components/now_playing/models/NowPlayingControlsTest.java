package com.edavtyan.materialplayer.components.now_playing.models;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingFactory;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;
import com.edavtyan.materialplayer.components.player.RepeatMode;
import com.edavtyan.materialplayer.components.player.ShuffleMode;
import com.edavtyan.materialplayer.lib.testable.TestableImageButton;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;
import com.edavtyan.materialplayer.utils.AppColors;

import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

public class NowPlayingControlsTest extends ActivityTest {
	private static NowPlayingActivity activity;

	private NowPlayingMvp.View.Controls controls;
	private NowPlayingMvp.Presenter presenter;
	private AppColors colors;

	private TestableImageButton shuffleButton;
	private TestableImageButton rewindButton;
	private TestableImageButton playPauseButton;
	private TestableImageButton fastForwardButton;
	private TestableImageButton repeatButton;

	@Override
	public void beforeEach() {
		super.beforeEach();

		colors = new AppColors(context);
		presenter = mock(NowPlayingMvp.Presenter.class);

		NowPlayingFactory factory = mock(NowPlayingFactory.class);
		when(factory.getAppColors()).thenReturn(colors);
		when(factory.getPresenter()).thenReturn(presenter);

		app.setNowPlayingFactory(factory);

		if (activity == null) {
			activity = startActivity(NowPlayingActivity.class);
		}

		shuffleButton = (TestableImageButton) activity.findViewById(R.id.shuffle);
		rewindButton = (TestableImageButton) activity.findViewById(R.id.rewind);
		playPauseButton = (TestableImageButton) activity.findViewById(R.id.playPause);
		fastForwardButton = (TestableImageButton) activity.findViewById(R.id.fastForward);
		repeatButton = (TestableImageButton) activity.findViewById(R.id.repeat);

		controls = new NowPlayingControls(activity, presenter, colors);
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
		assertThat(shuffleButton.getColorFilterColor()).isEqualTo(colors.accent);
	}

	@Test
	public void setShuffleMode_disabled_setShuffleButtonColorFilterToContrast() {
		runOnUiThread(() -> controls.setShuffleMode(ShuffleMode.DISABLED));
		assertThat(shuffleButton.getColorFilterColor()).isEqualTo(colors.textPrimary);
	}

	@Test
	public void setRepeatMode_repeatAll_setRepeatButtonColorFilterAndIcon() {
		runOnUiThread(() -> controls.setRepeatMode(RepeatMode.REPEAT_ALL));
		assertThat(repeatButton.getColorFilterColor()).isEqualTo(colors.accent);
		assertThat(repeatButton).hasImageResource(R.drawable.ic_repeat);
	}

	@Test
	public void setRepeatMode_repeatOne_setRepeatButtonColorFilterAndIcon() {
		runOnUiThread(() -> controls.setRepeatMode(RepeatMode.REPEAT_ONE));
		assertThat(repeatButton.getColorFilterColor()).isEqualTo(colors.accent);
		assertThat(repeatButton).hasImageResource(R.drawable.ic_repeat_one);
	}

	@Test
	public void setRepeatMode_disabled_setRepeatButtonColorFilterAndIcon() {
		runOnUiThread(() -> controls.setRepeatMode(RepeatMode.DISABLED));
		assertThat(repeatButton.getColorFilterColor()).isEqualTo(colors.textPrimary);
		assertThat(repeatButton).hasImageResource(R.drawable.ic_repeat);
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
