package com.edavtyan.materialplayer.ui.audio_effects.views;

import android.widget.SeekBar;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.audio_effects.views.DoubleSeekbar.OnProgressChangedListener;
import com.edavtyan.materialplayer.ui.audio_effects.views.DoubleSeekbar.OnStopTrackingTouchListener;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class DoubleSeekbarTest extends BaseTest {
	private DoubleSeekbar doubleSeekbar;
	private SeekBar innerSeekbar;

	@Override
	public void beforeEach() {
		super.beforeEach();

		doubleSeekbar = new DoubleSeekbar(context, null);
		innerSeekbar = (SeekBar) doubleSeekbar.findViewById(R.id.seekbar);
	}

	@Test
	public void setMax_setDoubledMax() {
		doubleSeekbar.setMax(100);
		assertThat(innerSeekbar.getMax()).isEqualTo(200);
	}

	@Test
	public void getMax_returnSetMax() {
		doubleSeekbar.setMax(150);
		assertThat(doubleSeekbar.getMax()).isEqualTo(150);
	}

	@Test
	public void setProgress_setProgressPlusMax() {
		doubleSeekbar.setMax(100);
		doubleSeekbar.setProgress(-25);
		assertThat(innerSeekbar.getProgress()).isEqualTo(75);
	}

	@Test
	public void getProgress_returnSetProgress() {
		doubleSeekbar.setProgress(-40);
		assertThat(doubleSeekbar.getProgress()).isEqualTo(-40);
	}

	@Test
	public void onStopTrackingTouch_listenerSet_callOnStopTrackingTouchListener() {
		OnStopTrackingTouchListener listener = mock(OnStopTrackingTouchListener.class);
		doubleSeekbar.setOnStopTrackingTouchListener(listener);
		doubleSeekbar.onStopTrackingTouch(innerSeekbar);
		verify(listener).onStopTrackingTouch();
	}

	@Test
	public void onStopTrackingTouch_listenerNotSet_notCallOnStopTrackingTouchListener() {
		OnStopTrackingTouchListener listener = mock(OnStopTrackingTouchListener.class);
		doubleSeekbar.onStopTrackingTouch(innerSeekbar);
		verify(listener, never()).onStopTrackingTouch();
	}

	@Test
	public void onProgressChanged_listenerSet_callOnProgressChangedListenerWithProgressPlusMax() {
		OnProgressChangedListener listener = mock(OnProgressChangedListener.class);
		doubleSeekbar.setMax(100);
		doubleSeekbar.setProgress(-20);
		doubleSeekbar.setOnProgressChangedListener(listener);
		doubleSeekbar.onProgressChanged(innerSeekbar, -20, true);
		verify(listener).onProgressChanged(-20);
	}
}
