package com.edavtyan.materialplayer.components.audio_effects.views;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.audio_effects.views.EqualizerView.OnBandChangedListener;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.assertions.NoNpeAssert.assertThatNPENotThrown;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EqualizerViewTest extends BaseTest {
	private EqualizerView equalizerView;

	@Override
	public void beforeEach() {
		super.beforeEach();
		equalizerView = new EqualizerView(context, null);
	}

	@Test
	public void constructor_initRootLayout() {
		assertThat(equalizerView.getOrientation()).isEqualTo(LinearLayout.VERTICAL);
	}

	@Test
	public void setBands_setCorrectBands() {
		int count = 5;
		int limit = 10;
		int[] frequencies = {5, 11, 19, 26, 30};
		int[] gains = {-5, -2, 0, 3, 6};

		equalizerView.setBands(count, frequencies, gains, limit);

		assertThat(equalizerView.getChildCount()).isEqualTo(count);
		for (int i = 0; i < equalizerView.getChildCount(); i++) {
			EqualizerBandView bandView = (EqualizerBandView) equalizerView.getChildAt(i);
			TextView frequencyView = (TextView) bandView.findViewById(R.id.frequency);
			TextView gainView = (TextView) bandView.findViewById(R.id.gain);
			DoubleSeekbar bandSeekbarView = (DoubleSeekbar) bandView.findViewById(R.id.band);
			assertThat(bandView.getIndex()).isEqualTo(i);
			assertThat(frequencyView.getText()).isEqualTo(frequencies[i] + " Hz");
			assertThat(bandSeekbarView.getMax()).isEqualTo(limit);

			if (gains[i] > 0)  {
				assertThat(gainView.getText()).isEqualTo("+" + gains[i] + " dB");
			} else {
				assertThat(gainView.getText()).isEqualTo(gains[i] + " dB");
			}
		}
	}

	@Test
	public void onBandChanged_listenerSetAndBandProgressChanged_called() {
		int count = 5;
		int limit = 10;
		int[] frequencies = {5, 11, 19, 26, 30};
		int[] gains = {-5, -2, 0, 3, 6};

		OnBandChangedListener listener = mock(OnBandChangedListener.class);
		equalizerView.setOnBandChangedListener(listener);
		equalizerView.setBands(count, frequencies, gains, limit);

		EqualizerBandView band = (EqualizerBandView) equalizerView.getChildAt(3);

		band.onProgressChanged(30);
		verify(listener).onBandChanged(band);

		band.onStopTrackingTouch();
		verify(listener).onBandStopTracking();
	}

	@Test
	public void onBandChanged_listenerNotSet_notThrowNPE() {
		assertThatNPENotThrown(() -> equalizerView.onBandChanged(null));
	}

	@Test
	public void onBandStopTracking_listenerNotSet_notThrowNPE() {
		assertThatNPENotThrown(equalizerView::onBandStopTracking);
	}
}
