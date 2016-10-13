package com.edavtyan.materialplayer.components.audioeffects.views;

import android.widget.LinearLayout;

import com.edavtyan.materialplayer.components.audioeffects.views.EqualizerView.OnBandChangedListener;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EqualizerViewTest extends BaseTest {
	private EqualizerView equalizerView;

	@Override public void beforeEach() {
		super.beforeEach();
		equalizerView = new EqualizerView(context, null);
	}

	@Test public void constructor_initRootLayout() {
		assertThat(equalizerView.getOrientation()).isEqualTo(LinearLayout.VERTICAL);
	}

	@Test public void setBands_setCorrectBands() {
		int count = 5;
		int limit = 10;
		int[] frequencies = {5, 11, 19, 26, 30};
		int[] gains = {-5, -2, 0, 3, 6};

		equalizerView.setBands(count, frequencies, gains, limit);

		assertThat(equalizerView.getChildCount()).isEqualTo(count);
		for (int i = 0; i < equalizerView.getChildCount(); i++) {
			EqualizerBandView bandView = (EqualizerBandView) equalizerView.getChildAt(i);
			assertThat(bandView.getIndex()).isEqualTo(i);
			assertThat(bandView.getFrequency()).isEqualTo(frequencies[i]);
			assertThat(bandView.getGain()).isEqualTo(gains[i]);
			assertThat(bandView.getGainLimit()).isEqualTo(limit);
		}
	}

	@Test public void onBandChanged_listenerSetAndBandProgressChanged_called() {
		int count = 5;
		int limit = 10;
		int[] frequencies = {5, 11, 19, 26, 30};
		int[] gains = {-5, -2, 0, 3, 6};

		OnBandChangedListener listener = mock(OnBandChangedListener.class);
		equalizerView.setOnBandChangedListener(listener);
		equalizerView.setBands(count, frequencies, gains, limit);

		EqualizerBandView band = (EqualizerBandView) equalizerView.getChildAt(3);

		band.onProgressChanged(mock(DoubleSeekbar.class), 30, true);
		verify(listener).onBandChanged(band);

		band.onStopTrackingTouch(mock(DoubleSeekbar.class));
		verify(listener).onBandStopTracking(band);
	}

	@Test public void onBandChanged_listenerNotSet_notThrowException() {
		try {
			equalizerView.onBandChanged(null, 0);
		} catch (NullPointerException e) {
			fail("Expected onBandChanged to not throw NullPointerException"
				 + "if OnBandChangedListener not set");
		}
	}

	@Test public void onBandStopTracking_listenerNotSet_notThrowException() {
		try {
			equalizerView.OnBandStopTracking(null);
		} catch (NullPointerException e) {
			fail("Expected onBandStopTracking to not throw NullPointerException"
				 + "if OnBandChangedListener not set");
		}
	}
}
