package com.edavtyan.materialplayer.components.audioeffects;

import com.edavtyan.materialplayer.components.audioeffects.models.BassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.Equalizer;
import com.edavtyan.materialplayer.components.audioeffects.models.Surround;
import com.edavtyan.materialplayer.components.audioeffects.views.EqualizerBandView;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AudioEffectsPresenterTest extends BaseTest {
	private AudioEffectsMvp.Presenter presenter;
	private AudioEffectsMvp.View view;
	private AudioEffectsMvp.Model model;
	private Equalizer equalizer;
	private BassBoost bassBoost;
	private Surround surround;

	@Override public void beforeEach() {
		super.beforeEach();
		model = mock(AudioEffectsMvp.Model.class);
		view = mock(AudioEffectsMvp.View.class);
		presenter = new AudioEffectsPresenter(model, view);

		equalizer = mock(Equalizer.class);
		bassBoost = mock(BassBoost.class);
		surround = mock(Surround.class);

		when(model.getEqualizer()).thenReturn(equalizer);
		when(model.getBassBoost()).thenReturn(bassBoost);
		when(model.getSurround()).thenReturn(surround);
	}

	@Test public void onServiceConnected_initViewUsingModelData() {
		when(equalizer.isEnabled()).thenReturn(true);
		when(equalizer.getGains()).thenReturn(new int[]{4, 5, 6, 7, 8});
		when(equalizer.getFrequencies()).thenReturn(new int[]{10, 20, 30, 40, 50});
		when(equalizer.getBandsCount()).thenReturn(5);
		when(equalizer.getGainLimit()).thenReturn(15);
		when(bassBoost.getStrength()).thenReturn(15);
		when(bassBoost.getMaxStrength()).thenReturn(100);
		when(surround.getStrength()).thenReturn(4);
		when(surround.getMaxStrength()).thenReturn(200);

		presenter.onServiceConnected();

		verify(view).setEqualizerEnabled(true);
		verify(view).setEqualizerBands(5, 15, new int[]{10, 20, 30, 40, 50}, new int[]{4, 5, 6, 7, 8});
		verify(view).initBassBoost(100, 15);
		verify(view).initSurround(200, 4);
	}

	@Test public void onCreate_initModel() {
		presenter.onCreate();
		verify(model).init();
	}

	@Test public void onDestroy_closeModel() {
		presenter.onDestroy();
		verify(model).close();
	}

	@Test public void onEqualizerEnabledChanged_callModel() {
		presenter.onEqualizerEnabledChanged(true);
		verify(equalizer).setEnabled(true);
	}

	@Test public void onEqualizerBandChanged_setBandGainViaModel() {
		EqualizerBandView band = mock(EqualizerBandView.class);
		when(band.getIndex()).thenReturn(3);
		when(band.getGain()).thenReturn(9);
		presenter.onEqualizerBandChanged(band);
		verify(equalizer).setBandGain(3, 9);
	}

	@Test public void onEqualizerBandStopTracking_saveEqualizerGainSettingsViaModel() {
		presenter.onEqualizerBandStopTracking();
		verify(equalizer).saveSettings();
	}

	@Test public void onBassBoostStrengthChanged_setBassBoostStrengthViaModel() {
		presenter.onBassBoostStrengthChanged(30);
		verify(bassBoost).setStrength(30);
	}

	@Test public void onBassBoostStrengthStopChanging_saveBassBoostStrengthViaModel() {
		presenter.onBassBoostStrengthStopChanging();
		verify(bassBoost).saveSettings();
	}

	@Test public void onSurroundStrengthChanged_setSurroundStrengthViaModel() {
		presenter.onSurroundStrengthChanged(40);
		verify(surround).setStrength(40);
	}

	@Test public void onSurroundStrengthStopChanging_saveSurroundStrengthViaModel() {
		presenter.onSurroundStrengthStopChanging();
		verify(surround).saveSettings();
	}
}
