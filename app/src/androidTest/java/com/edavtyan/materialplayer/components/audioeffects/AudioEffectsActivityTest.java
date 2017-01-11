package com.edavtyan.materialplayer.components.audioeffects;

import android.annotation.SuppressLint;
import android.support.v7.widget.SwitchCompat;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.audioeffects.views.EqualizerBandView;
import com.edavtyan.materialplayer.components.audioeffects.views.EqualizerView;
import com.edavtyan.materialplayer.components.audioeffects.views.TitledSeekbar;
import com.edavtyan.materialplayer.testlib.tests.ActivityTest;

import org.junit.Test;

import static com.edavtyan.materialplayer.R.id.equalizer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class AudioEffectsActivityTest extends ActivityTest {
	private static AudioEffectsActivity activity;
	private static AudioEffectsMvp.Presenter presenter;
	private static SwitchCompat equalizerSwitch;
	private static EqualizerView equalizerView;
	private static TitledSeekbar bassBoostView;
	private static TitledSeekbar amplifierView;
	private static TitledSeekbar surroundView;

	@Override public void beforeEach() {
		super.beforeEach();

		if (activity == null) {
			presenter = mock(AudioEffectsMvp.Presenter.class);

			AudioEffectsFactory factory = mock(AudioEffectsFactory.class);
			when(factory.providePresenter()).thenReturn(presenter);
			when(app.getAudioEffectsFactory(any(), any())).thenReturn(factory);

			activity = spy(startActivity(AudioEffectsActivity.class));
			doNothing().when(activity).baseOnCreate(any());
			doNothing().when(activity).baseOnDestroy();
			doReturn(app).when(activity).getApplicationContext();
		} else {
			reset(presenter, equalizerSwitch, equalizerView, bassBoostView, amplifierView, surroundView);
		}

		equalizerSwitch = spy(new SwitchCompat(context));
		equalizerView = spy(new EqualizerView(context, null));
		bassBoostView = spy(new TitledSeekbar(context, null));
		amplifierView = spy(new TitledSeekbar(context, null));
		surroundView = spy(new TitledSeekbar(context, null));

		doReturn(equalizerSwitch).when(activity).findView(R.id.equalizerSwitch);
		doReturn(equalizerView).when(activity).findView(equalizer);
		doReturn(bassBoostView).when(activity).findView(R.id.bassBoost);
		doReturn(surroundView).when(activity).findView(R.id.surround);
		doReturn(amplifierView).when(activity).findView(R.id.amplifier);

		runOnUiThread(() -> activity.onCreate(null));
	}

	@Test public void onCreate_initPresenter() {
		verify(presenter).onCreate();
	}

	@Test public void onCreate_setEventListeners() {
		verify(equalizerSwitch).setOnCheckedChangeListener(activity);
		verify(equalizerView).setOnBandChangedListener(activity);
		verify(bassBoostView).setOnProgressChangedListener(activity);
		verify(surroundView).setOnProgressChangedListener(activity);
	}

	@Test public void onDestroy_disposePresenter() {
		activity.onDestroy();
		verify(presenter).onDestroy();
	}

	@Test public void getLayoutId_returnCorrectLayoutId() {
		assertThat(activity.getLayoutId()).isEqualTo(R.layout.activity_effects);
	}

	@Test public void setEqualizerEnabled_true_setSwitchToEnabled() {
		activity.setEqualizerEnabled(true);
		verify(equalizerSwitch).setChecked(true);
	}

	@Test public void equalizerSwitchClicked_switchEqualizerViaPresenter() {
		equalizerSwitch.performClick();
		verify(presenter).onEqualizerEnabledChanged(true);
	}

	@Test public void setEqualizerBands_createCorrectBands() {
		final int count = 5;
		final int gainLimit = 15;
		final int[] frequencies = {31, 62, 125, 250, 500};
		final int[] gains = {-3, -1, 0, 2, 4};

		activity.setEqualizerBands(count, gainLimit, frequencies, gains);

		verify(equalizerView).setBands(count, frequencies, gains, gainLimit);
	}

	@Test public void onBandChanged_callPresenter() {
		EqualizerBandView band = mock(EqualizerBandView.class);
		activity.onBandChanged(band);
		verify(presenter).onEqualizerBandChanged(band);
	}

	@Test public void onBandStopTracking_callPresenter() {
		activity.onBandStopTracking();
		verify(presenter).onEqualizerBandStopTracking();
	}

	@Test public void setBassBoostStrength_setBassBoostSeekbarMaxAndProgress() {
		activity.initBassBoost(100, 5);
		verify(bassBoostView).setMax(100);
		verify(bassBoostView).setProgress(5);
	}

	@Test public void onProgressChanged_bassBoostId_changeBassBoostStrengthViaPresenter() {
		activity.onProgressChange(R.id.bassBoost, 10);
		verify(presenter).onBassBoostStrengthChanged(10);
	}

	@Test public void onStopTrackingTouch_bassBoostId_callPresenter() {
		activity.onStopTrackingTouch(R.id.bassBoost);
		verify(presenter).onBassBoostStrengthStopChanging();
	}

	@Test public void setSurroundStrength_setSurroundSeekbarMaxAndProgress() {
		activity.initSurround(200, 15);
		verify(surroundView).setProgress(15);
		verify(surroundView).setMax(200);
	}

	@Test public void onProgressChanged_surroundId_changSurroundStrengthViaPresenter() {
		activity.onProgressChange(R.id.surround, 20);
		verify(presenter).onSurroundStrengthChanged(20);
	}

	@Test public void onStopTrackingTouch_surroundId_callPresenter() {
		activity.onStopTrackingTouch(R.id.surround);
		verify(presenter).onSurroundStrengthStopChanging();
	}

	@Test public void initAmplifier_setAmplifierSeekbarMaxAndProgress() {
		activity.initAmplifier(300, 25);
		verify(amplifierView).setProgress(25);
		verify(amplifierView).setMax(300);
	}

	@Test public void onProgressChanged_amplifierId_changAmplifierStrengthViaPresenter() {
		activity.onProgressChange(R.id.amplifier, 30);
		verify(presenter).onAmplifierStrengthChanged(30);
	}

	@Test public void onStopTrackingTouch_amplifierId_callPresenter() {
		activity.onStopTrackingTouch(R.id.amplifier);
		verify(presenter).onAmplifierStrengthStopChanging();
	}

	@Test public void onProgressChanged_otherId_notCallPresenter() {
		activity.onProgressChange(-1, -10);
		verify(presenter, never()).onBassBoostStrengthChanged(-10);
		verify(presenter, never()).onSurroundStrengthChanged(-10);
	}

	@Test public void onStopTrackingTouch_otherId_notCallPresenter() {
		activity.onStopTrackingTouch(-1);
		verify(presenter, never()).onSurroundStrengthStopChanging();
		verify(presenter, never()).onSurroundStrengthStopChanging();
		verify(presenter, never()).onSurroundStrengthStopChanging();
	}
}
