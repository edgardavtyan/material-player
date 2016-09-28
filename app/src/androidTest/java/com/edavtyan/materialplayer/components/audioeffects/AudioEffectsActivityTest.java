package com.edavtyan.materialplayer.components.audioeffects;

import android.annotation.SuppressLint;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.v7.widget.SwitchCompat;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.audioeffects.views.EqualizerBandView;
import com.edavtyan.materialplayer.components.audioeffects.views.EqualizerView;
import com.edavtyan.materialplayer.components.audioeffects.views.TitledSeekbar;
import com.edavtyan.materialplayer.lib.db.ActivityTest;

import org.junit.BeforeClass;
import org.junit.Test;

import static com.edavtyan.materialplayer.R.id.equalizer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class AudioEffectsActivityTest extends ActivityTest {
	private static AudioEffectsActivity2 staticActivity;

	private AudioEffectsActivity2 activity;
	private AudioEffectsMvp.Presenter presenter;
	private SwitchCompat equalizerSwitch;
	private EqualizerView equalizerView;
	private TitledSeekbar bassBoostView;
	private TitledSeekbar amplifierView;
	private TitledSeekbar surroundView;

	@BeforeClass
	public static void beforeClass() {
		ActivityTest.beforeClass();

		Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
		Intent intent = new Intent(context, AudioEffectsActivity2.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		staticActivity = (AudioEffectsActivity2) instrumentation.startActivitySync(intent);
	}

	@Override
	public void beforeEach() {
		super.beforeEach();

		presenter = mock(AudioEffectsMvp.Presenter.class);

		AudioEffectsFactory factory = mock(AudioEffectsFactory.class);
		when(factory.providePresenter()).thenReturn(presenter);
		when(app.getAudioEffectsFactory(any(), any())).thenReturn(factory);

		equalizerSwitch = spy(new SwitchCompat(context));
		equalizerView = spy(new EqualizerView(context, null));
		bassBoostView = spy(new TitledSeekbar(context, null));
		amplifierView = spy(new TitledSeekbar(context, null));
		surroundView = spy(new TitledSeekbar(context, null));

		activity = spy(staticActivity);
		doNothing().when(activity).baseOnCreate(any());
		doNothing().when(activity).baseOnDestroy();
		doReturn(app).when(activity).getApplicationContext();
		doReturn(equalizerSwitch).when(activity).findViewById(R.id.equalizerSwitch);
		doReturn(equalizerView).when(activity).findViewById(equalizer);
		doReturn(bassBoostView).when(activity).findViewById(R.id.bassBoost);
		doReturn(amplifierView).when(activity).findViewById(R.id.amplifier);
		doReturn(surroundView).when(activity).findViewById(R.id.surround);
	}

	@Test
	public void onCreate_initPresenter() {
		activity.onCreate(null);
		verify(presenter).onCreate();
	}

	@Test
	public void onCreate_setEventListeners() {
		activity.onCreate(null);
		verify(equalizerSwitch).setOnCheckedChangeListener(activity);
		verify(equalizerView).setOnBandChangedListener(activity);
		verify(bassBoostView).setOnProgressChangedListener(activity);
		verify(amplifierView).setOnProgressChangedListener(activity);
		verify(surroundView).setOnProgressChangedListener(activity);
	}

	@Test
	public void onDestroy_disposePresenter() {
		activity.onCreate(null);
		activity.onDestroy();
		verify(presenter).onDestroy();
	}

	@Test
	public void getLayoutId_returnCorrectLayoutId() {
		activity.onCreate(null);
		assertThat(activity.getLayoutId()).isEqualTo(R.layout.activity_effects);
	}

	@Test
	public void setEqualizerEnabled_true_setSwitchToEnabled() {
		activity.onCreate(null);
		activity.setEqualizerEnabled(true);
		verify(equalizerSwitch).setChecked(true);
	}

	@Test
	public void equalizerSwitchClicked_switchEqualizerViaPresenter() {
		activity.onCreate(null);
		equalizerSwitch.performClick();
		verify(presenter).onEqualizerEnabledChanged(true);
	}

	@Test
	public void setEqualizerBands_createCorrectBands() {
		final int count = 5;
		final int gainLimit = 15;
		final int[] frequencies = {31, 62, 125, 250, 500};
		final int[] gains = {-15, -5, 0, 4, 10};

		activity.onCreate(null);
		activity.setEqualizerBands(count, gainLimit, frequencies, gains);

		verify(equalizerView).setBands(count, frequencies, gains, gainLimit);
	}

	@Test
	public void onBandChanged_callPresenter() {
		EqualizerBandView band = mock(EqualizerBandView.class);
		activity.onCreate(null);
		activity.onBandChanged(band);
		verify(presenter).onEqualizerBandChanged(band);
	}

	@Test
	public void onBandStopTracking_callPresenter() {
		activity.onCreate(null);
		activity.onBandStopTracking(mock(EqualizerBandView.class));
		verify(presenter).onEqualizerBandStopTracking();
	}

	@Test
	public void setBassBoostStrength_setBassBoostSeekbarMaxAndProgress() {
		activity.onCreate(null);
		activity.initBassBoost(100, 45);
		verify(bassBoostView).setMax(100);
		verify(bassBoostView).setProgress(45);
	}

	@Test
	public void onProgressChanged_bassBoostId_changeBassBoostStrengthViaPresenter() {
		activity.onCreate(null);
		activity.onProgressChange(R.id.bassBoost, 90);
		verify(presenter).onBassBoostStrengthChanged(90);
	}

	@Test
	public void onStopTrackingTouch_bassBoostId_callPresenter() {
		activity.onCreate(null);
		activity.onStopTrackingTouch(R.id.bassBoost);
		verify(presenter).onBassBoostStrengthStopChanging();
	}

	@Test
	public void initAmplifier_setAmplifierSeekbarMaxAndProgress() {
		activity.onCreate(null);
		activity.initAmplifier(200, 55);
		verify(amplifierView).setMax(200);
		verify(amplifierView).setProgress(55);
	}

	@Test
	public void onProgressChanged_amplifierId_changAmplifierStrengthViaPresenter() {
		activity.onCreate(null);
		activity.onProgressChange(R.id.amplifier, 90);
		verify(presenter).onAmplifierStrengthChanged(90);
	}

	@Test
	public void onStopTrackingTouch_amplifierId_callPresenter() {
		activity.onCreate(null);
		activity.onStopTrackingTouch(R.id.amplifier);
		verify(presenter).onAmplifierStrengthStopChanging();
	}

	@Test
	public void setSurroundStrength_setSurroundSeekbarMaxAndProgress() {
		activity.onCreate(null);
		activity.initSurround(300, 65);
		verify(surroundView).setProgress(65);
		verify(surroundView).setMax(300);
	}

	@Test
	public void onProgressChanged_surroundId_changAmplifierStrengthViaPresenter() {
		activity.onCreate(null);
		activity.onProgressChange(R.id.surround, 90);
		verify(presenter).onSurroundStrengthChanged(90);
	}

	@Test
	public void onStopTrackingTouch_surroundId_callPresenter() {
		activity.onCreate(null);
		activity.onStopTrackingTouch(R.id.surround);
		verify(presenter).onSurroundStrengthStopChanging();
	}
}
