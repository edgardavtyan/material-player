package com.edavtyan.materialplayer.screens.audio_effects.views;

import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.screens.audio_effects.views.EqualizerBandView.OnBandChangedListener;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static com.edavtyan.materialplayer.testlib.assertions.NoNpeAssert.assertThatNPENotThrown;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EqualizerBandViewTest extends BaseTest {

	private EqualizerBandView equalizerView;
	private DoubleSeekbar bandView;
	private TextView gainView;
	private TextView frequencyView;

	@Override
	public void beforeEach() {
		super.beforeEach();
		equalizerView = new EqualizerBandView(context, null);
		bandView = (DoubleSeekbar) equalizerView.findViewById(R.id.band);
		gainView = (TextView) equalizerView.findViewById(R.id.gain);
		frequencyView = (TextView) equalizerView.findViewById(R.id.frequency);
	}

	@Test
	public void setGainLimit_setSeekbarMax() {
		equalizerView.setGainLimit(10);
		assertThat(bandView.getMax()).isEqualTo(10);
	}

	@Test
	public void setGain_setBandProgress() {
		equalizerView.setGain(20);
		assertThat(bandView.getProgress()).isEqualTo(20);
	}

	@Test
	public void setGain_positiveGain_setGainTextWithPlusSign() {
		equalizerView.setGain(30);
		assertThat(gainView.getText()).isEqualTo("+30 dB");
	}

	@Test
	public void setGain_zero_setGainTextWithoutSign() {
		equalizerView.setGain(0);
		assertThat(gainView.getText()).isEqualTo("0 dB");
	}

	@Test
	public void setGain_negativeGain_setGainsWithMinusSign() {
		equalizerView.setGain(-40);
		assertThat(gainView.getText()).isEqualTo("-40 dB");
	}

	@Test
	public void setFrequency_frequencyLessThatThousand_setFrequencyViewTextAsIsWithHertz() {
		equalizerView.setFrequency(999);
		assertThat(frequencyView.getText()).isEqualTo("999 Hz");
	}

	@Test
	public void setFrequency_frequencyIsThousandOrBigger_setFrequencyAsKiloHertzRoundedToFirstDecimal() {
		equalizerView.setFrequency(14789);
		assertThat(frequencyView.getText()).isEqualTo("14.8 kHz");
	}

	@Test
	public void setFrequency_frequencyIsDivisibleByThousand_setFrequencyWithoutDecimalPoint() {
		equalizerView.setFrequency(16000);
		assertThat(frequencyView.getText()).isEqualTo("16 kHz");
	}

	@Test
	public void onProgressChanged_progressIsPositive_setGainViewTextWithPlusSign() {
		equalizerView.onProgressChanged(50);
		assertThat(gainView.getText()).isEqualTo("+50 dB");
	}

	@Test
	public void onProgressChanged_progressIsZero_setGainViewTextWithoutSign() {
		equalizerView.onProgressChanged(0);
		assertThat(gainView.getText()).isEqualTo("0 dB");
	}

	@Test
	public void onProgressChanged_progressIsNegative_setGainViewTextWithMinusSign() {
		equalizerView.onProgressChanged(-60);
		assertThat(gainView.getText()).isEqualTo("-60 dB");
	}

	@Test
	public void onProgressChanged_onBandChangedListenerSet_callOnBandChangedWithEqualizerViewAndProgress() {
		OnBandChangedListener listener = mock(OnBandChangedListener.class);
		equalizerView.setOnBandChangedListener(listener);
		equalizerView.onProgressChanged(70);
		verify(listener).onBandChanged(equalizerView);
	}

	@Test
	public void onProgressChanged_onBandChangedListenerNotSet_notThrowException() {
		assertThatNPENotThrown(() -> equalizerView.onProgressChanged(80));
	}

	@Test
	public void onStopTrackingTouch_onBandChangedListenerSet_callOnBandStopTracking() {
		OnBandChangedListener listener = mock(OnBandChangedListener.class);
		equalizerView.setOnBandChangedListener(listener);
		equalizerView.onStopTrackingTouch();
		verify(listener).onBandStopTracking();
	}

	@Test
	public void onStopTrackingTouch_onBandChangedListenerNotSet_notThrowException() {
		assertThatNPENotThrown(equalizerView::onStopTrackingTouch);
	}
}
