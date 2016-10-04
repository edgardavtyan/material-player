package com.edavtyan.materialplayer.components.now_playing.models;

import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;
import com.edavtyan.materialplayer.lib.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NowPlayingSeekbarTest extends BaseTest {
	private NowPlayingMvp.View.Seekbar seekbar;
	private NowPlayingMvp.Presenter presenter;

	private SeekBar seekbarView;
	private TextView currentTimeView;
	private TextView totalTimeView;

	@Override
	public void beforeEach() {
		super.beforeEach();

		seekbarView = spy(new SeekBar(context));
		currentTimeView = spy(new TextView(context));
		totalTimeView = spy(new TextView(context));

		NowPlayingActivity activity = mock(NowPlayingActivity.class);
		when(activity.findView(R.id.seekbar)).thenReturn(seekbarView);
		when(activity.findView(R.id.timeCurrent)).thenReturn(currentTimeView);
		when(activity.findView(R.id.timeTotal)).thenReturn(totalTimeView);

		presenter = mock(NowPlayingMvp.Presenter.class);
		seekbar = new NowPlayingSeekbar(activity, presenter);
	}

	@Test
	public void setTrackPosition_setSeekbarViewProgress() {
		seekbar.setTrackPosition(50);
		assertThat(seekbarView.getProgress()).isEqualTo(50);
	}

	@Test
	public void setTrackPositionText_setCurrentTimeViewText() {
		seekbar.setTrackPositionText(14800);
		assertThat(currentTimeView.getText()).isEqualTo("00:14");
	}

	@Test
	public void setTrackDuration_setSeekbarViewMax() {
		seekbar.setTrackDuration(600);
		assertThat(seekbarView.getMax()).isEqualTo(600);
	}

	@Test
	public void setTrackDurationText_setTotalTimeViewText() {
		seekbar.setTrackDurationText(8000);
		assertThat(totalTimeView.getText()).isEqualTo("00:08");
	}

	@Test
	public void onProgressChanged_fromUser_callPresenter() {
		seekbar.onProgressChanged(seekbarView, 30, true);
		verify(presenter).onTrackSeekChanged(30);
	}

	@Test
	public void onProgressChanged_notFromUser_notCallPresenter() {
		seekbar.onProgressChanged(seekbarView, 40, false);
		verify(presenter, never()).onTrackSeekChanged(40);
	}

	@Test
	public void onStopTrackingTouch_callPresenter() {
		when(seekbarView.getProgress()).thenReturn(9000);
		seekbar.onStopTrackingTouch(seekbarView);
		verify(presenter).onTrackSeekStop(9000);
	}
}
