package com.edavtyan.materialplayer.components.audio_effects;

import android.annotation.SuppressLint;
import android.support.v7.widget.SwitchCompat;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.audio_effects.views.EqualizerBandView;
import com.edavtyan.materialplayer.components.audio_effects.views.EqualizerView;
import com.edavtyan.materialplayer.components.audio_effects.views.TitledSeekbar;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Test;

import static com.edavtyan.materialplayer.R.id.equalizer;
import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class AudioEffectsActivityTest extends ActivityTest {
	private static AudioEffectsActivity activity;
	private static AudioEffectsPresenter presenter;
	private static SwitchCompat equalizerSwitch;
	private static EqualizerView equalizerView;
	private static TitledSeekbar bassBoostView;
	private static TitledSeekbar amplifierView;
	private static TitledSeekbar surroundView;

	@Override
	public void beforeEach() {
		super.beforeEach();

		if (activity == null) {
			presenter = mock(AudioEffectsPresenter.class);

			AudioEffectsViewFactory factory = mock(AudioEffectsViewFactory.class);
			when(factory.getPresenter()).thenReturn(presenter);

			app.setAudioEffectsViewFactory(factory);

			activity = spy(startActivity(AudioEffectsActivity.class));
			doNothing().when(activity).baseOnCreate(any());
			doNothing().when(activity).baseOnDestroy();
		} else {
			reset(presenter);
		}

		equalizerSwitch = (SwitchCompat) activity.findViewById(R.id.equalizer_switch);
		equalizerView = (EqualizerView) activity.findViewById(equalizer);
		bassBoostView = (TitledSeekbar) activity.findViewById(R.id.bass_boost);
		surroundView = (TitledSeekbar) activity.findViewById(R.id.surround);
		amplifierView = (TitledSeekbar) activity.findViewById(R.id.amplifier);
	}

	@Test
	public void onCreate_initPresenter() {
		runOnUiThread(() -> activity.onCreate(null));
		verify(presenter).onCreate();
	}

	@Test
	public void onDestroy_disposePresenter() {
		activity.onDestroy();
		verify(presenter).onDestroy();
	}

	@Test
	public void getLayoutId_returnCorrectLayoutId() {
		assertThat(activity.getLayoutId()).isEqualTo(R.layout.activity_effects);
	}

	@Test
	public void setEqualizerEnabled_true_setSwitchToEnabled() {
		runOnUiThread(() -> activity.setEqualizerEnabled(true));
		assertThat(equalizerSwitch.isChecked()).isTrue();
	}

	@Test
	public void equalizerSwitchClicked_switchEqualizerViaPresenter() {
		runOnUiThread(() -> equalizerSwitch.performClick());
		verify(presenter).onEqualizerEnabledChanged(true);
	}

	@Test
	public void setEqualizerBands_createCorrectBands() {
		final int count = 5;
		final int gainLimit = 15;
		final int[] frequencies = {31, 62, 125, 250, 500};
		final int[] gains = {-3, -1, 0, 2, 4};

		runOnUiThread(() -> activity.setEqualizerBands(count, gainLimit, frequencies, gains));

		for (int i = 0; i < count; i++)
			assertThat((EqualizerBandView) equalizerView.getChildAt(i))
					.hasGainLimit(15)
					.hasFrequency(frequencies[i])
					.hasGain(gains[i]);
	}

	@Test
	public void onBandChanged_callPresenter() {
		EqualizerBandView band = mock(EqualizerBandView.class);
		runOnUiThread(() -> activity.onBandChanged(band));
		verify(presenter).onEqualizerBandChanged(band);
	}

	@Test
	public void onBandStopTracking_callPresenter() {
		runOnUiThread(() -> activity.onBandStopTracking());
		verify(presenter).onEqualizerBandStopTracking();
	}

	@Test
	public void setBassBoostStrength_setBassBoostSeekbarMaxAndProgress() {
		runOnUiThread(() -> activity.initBassBoost(100, 5));
		assertThat(bassBoostView)
				.hasMax(100)
				.hasProgress(5);
	}

	@Test
	public void onProgressChanged_bassBoostId_changeBassBoostStrengthViaPresenter() {
		runOnUiThread(() -> activity.onProgressChange(R.id.bass_boost, 10));
		verify(presenter).onBassBoostStrengthChanged(10);
	}

	@Test
	public void onStopTrackingTouch_bassBoostId_callPresenter() {
		runOnUiThread(() -> activity.onStopTrackingTouch(R.id.bass_boost));
		verify(presenter).onBassBoostStrengthStopChanging();
	}

	@Test
	public void setSurroundStrength_setSurroundSeekbarMaxAndProgress() {
		runOnUiThread(() -> activity.initSurround(200, 15));
		assertThat(surroundView)
				.hasMax(200)
				.hasProgress(15);
	}

	@Test
	public void onProgressChanged_surroundId_changSurroundStrengthViaPresenter() {
		runOnUiThread(() -> activity.onProgressChange(R.id.surround, 20));
		verify(presenter).onSurroundStrengthChanged(20);
	}

	@Test
	public void onStopTrackingTouch_surroundId_callPresenter() {
		runOnUiThread(() -> activity.onStopTrackingTouch(R.id.surround));
		verify(presenter).onSurroundStrengthStopChanging();
	}

	@Test
	public void initAmplifier_setAmplifierSeekbarMaxAndProgress() {
		runOnUiThread(() -> activity.initAmplifier(300, 25));
		assertThat(amplifierView)
				.hasMax(300)
				.hasProgress(25);
	}

	@Test
	public void onProgressChanged_amplifierId_changAmplifierStrengthViaPresenter() {
		runOnUiThread(() -> activity.onProgressChange(R.id.amplifier, 30));
		verify(presenter).onAmplifierStrengthChanged(30);
	}

	@Test
	public void onStopTrackingTouch_amplifierId_callPresenter() {
		runOnUiThread(() -> activity.onStopTrackingTouch(R.id.amplifier));
		verify(presenter).onAmplifierStrengthStopChanging();
	}

	@Test
	public void onProgressChanged_otherId_notCallPresenter() {
		runOnUiThread(() -> activity.onProgressChange(-1, -10));
		verify(presenter, never()).onBassBoostStrengthChanged(-10);
		verify(presenter, never()).onSurroundStrengthChanged(-10);
	}

	@Test
	public void onStopTrackingTouch_otherId_notCallPresenter() {
		runOnUiThread(() -> activity.onStopTrackingTouch(-1));
		verify(presenter, never()).onSurroundStrengthStopChanging();
		verify(presenter, never()).onSurroundStrengthStopChanging();
		verify(presenter, never()).onSurroundStrengthStopChanging();
	}
}
