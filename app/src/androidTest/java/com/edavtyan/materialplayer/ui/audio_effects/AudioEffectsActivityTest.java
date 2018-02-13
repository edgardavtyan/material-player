package com.edavtyan.materialplayer.ui.audio_effects;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.test.filters.SdkSuppress;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.SwitchCompat;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.audio_effects.views.EqualizerBandView;
import com.edavtyan.materialplayer.ui.audio_effects.views.EqualizerView;
import com.edavtyan.materialplayer.ui.audio_effects.views.TitledSeekbar;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Rule;
import org.junit.Test;

import static com.edavtyan.materialplayer.R.id.equalizer;
import static com.edavtyan.materialplayer.testlib.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SuppressLint("StaticFieldLeak")
public class AudioEffectsActivityTest extends ActivityTest {
	private static AudioEffectsPresenter presenter;

	public static class TestAudioEffectsActivity extends AudioEffectsActivity {
		@Override
		public void onCreate(@Nullable Bundle savedInstanceState) {
			this.toolbarModule = mock(ActivityToolbarModule.class);
			this.baseMenuModule = mock(ActivityBaseMenuModule.class);
			this.themeModule = mock(ScreenThemeModule.class);
			this.presenter = AudioEffectsActivityTest.presenter;
			super.onCreate(savedInstanceState);
		}

		@Override
		protected AudioEffectsComponent getComponent() {
			return mock(AudioEffectsComponent.class);
		}
	}

	@Rule
	public final ActivityTestRule<TestAudioEffectsActivity> activityRule
			= new ActivityTestRule<>(TestAudioEffectsActivity.class, false, false);

	private AudioEffectsActivity activity;
	private SwitchCompat equalizerSwitch;
	private EqualizerView equalizerView;
	private TitledSeekbar bassBoostView;
	private TitledSeekbar amplifierView;
	private TitledSeekbar surroundView;

	@Override
	public void beforeEach() {
		super.beforeEach();

		presenter = mock(AudioEffectsPresenter.class);

		activity = activityRule.launchActivity(null);

		equalizerSwitch = (SwitchCompat) activity.findViewById(R.id.equalizer_switch);
		equalizerView = (EqualizerView) activity.findViewById(equalizer);
		bassBoostView = (TitledSeekbar) activity.findViewById(R.id.bass_boost);
		surroundView = (TitledSeekbar) activity.findViewById(R.id.surround);
		amplifierView = (TitledSeekbar) activity.findViewById(R.id.amplifier);
	}

	@Test
	public void onCreate_initPresenter() {
		verify(presenter).onCreate();
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
	@SdkSuppress(minSdkVersion = 19)
	public void initAmplifier_setAmplifierSeekbarMaxAndProgress() {
		runOnUiThread(() -> activity.initAmplifier(300, 25));
		assertThat(amplifierView)
				.hasMax(300)
				.hasProgress(25);
	}

	@Test
	@SdkSuppress(minSdkVersion = 19)
	public void onProgressChanged_amplifierId_changAmplifierStrengthViaPresenter() {
		runOnUiThread(() -> activity.onProgressChange(R.id.amplifier, 30));
		verify(presenter).onAmplifierStrengthChanged(30);
	}

	@Test
	@SdkSuppress(minSdkVersion = 19)
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
