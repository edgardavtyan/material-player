package com.edavtyan.materialplayer.components.now_playing.models;

import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class NowPlayingSeekbarTest extends NowPlayingViewTest {
	private NowPlayingMvp.View.Seekbar seekbar;
	private SeekBar seekbarView;

	@Override
	public void beforeEach() {
		super.beforeEach();
		seekbar = new NowPlayingSeekbar(activity, presenter);
		seekbarView = (SeekBar) activity.findViewById(R.id.seekbar);
	}

	@Test
	public void setTrackPosition_setSeekbarViewProgress() {
		runOnUiThread(() -> seekbar.setPosition(50));
		assertThat(seekbarView.getProgress()).isEqualTo(50);
	}

	@Test
	public void setTrackPositionText_setCurrentTimeViewText() {
		TextView currentTimeView  = (TextView) activity.findViewById(R.id.time_current);
		runOnUiThread(() -> seekbar.setPositionText(14800));
		assertThat(currentTimeView.getText()).isEqualTo("00:14");
	}

	@Test
	public void setTrackDuration_setSeekbarViewMax() {
		runOnUiThread(() -> seekbar.setDuration(600));
		assertThat(seekbarView.getMax()).isEqualTo(600);
	}

	@Test
	public void setTrackDurationText_setTotalTimeViewText() {
		TextView totalTimeView  = (TextView) activity.findViewById(R.id.time_total);
		runOnUiThread(() -> seekbar.setDurationText(8000));
		assertThat(totalTimeView.getText()).isEqualTo("00:08");
	}

	@Test
	public void onProgressChanged_fromUser_callPresenter() {
		runOnUiThread(() -> seekbar.onProgressChanged(seekbarView, 30, true));
		verify(presenter).onSeekChanged(30);
	}

	@Test
	public void onProgressChanged_notFromUser_notCallPresenter() {
		runOnUiThread(() -> seekbar.onProgressChanged(seekbarView, 40, false));
		verify(presenter, never()).onSeekChanged(40);
	}

	@Test
	public void onStopTrackingTouch_callPresenter() {
		seekbarView.setProgress(50);
		runOnUiThread(() -> seekbar.onStopTrackingTouch(seekbarView));
		verify(presenter).onSeekStop(50);
	}
}
