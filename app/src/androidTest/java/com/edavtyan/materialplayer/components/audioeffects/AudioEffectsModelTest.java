package com.edavtyan.materialplayer.components.audioeffects;

import android.content.Context;
import android.content.Intent;
import android.support.v7.view.ContextThemeWrapper;

import com.edavtyan.materialplayer.components.audioeffects.models.Amplifier;
import com.edavtyan.materialplayer.components.audioeffects.models.BassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.Equalizer;
import com.edavtyan.materialplayer.components.audioeffects.models.Surround;
import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.components.player.PlayerService.PlayerBinder;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static com.edavtyan.materialplayer.testlib.asertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AudioEffectsModelTest extends BaseTest {
	private AudioEffectsMvp.Model model;
	private PlayerService service;
	private PlayerBinder binder;

	@Override
	public void beforeEach() {
		super.beforeEach();

		context = mock(ContextThemeWrapper.class);
		model = new AudioEffectsModel(context);
		service = mock(PlayerService.class);
		binder = mock(PlayerBinder.class);
		when(binder.getService()).thenReturn(service);
	}

	@SuppressWarnings("WrongConstant")
	@Test
	public void init_bindService() {
		model.init();

		ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);
		verify(context).bindService(intentCaptor.capture(), eq(model), eq(Context.BIND_AUTO_CREATE));
		assertThat(intentCaptor.getValue()).hasClass(PlayerService.class);
	}

	@Test
	public void close_unbindService() {
		model.close();
		verify(context).unbindService(model);
	}

	@Test
	public void getEqualizer_returnEqualizerFromService() {
		Equalizer equalizer = mock(Equalizer.class);
		when(service.getEqualizer()).thenReturn(equalizer);
		model.onServiceConnected(null, binder);
		assertThat(model.getEqualizer()).isEqualTo(equalizer);
	}

	@Test
	public void getBassBoost_returnBassBoostFromService() {
		BassBoost bassBoost = mock(BassBoost.class);
		when(service.getBassBoost()).thenReturn(bassBoost);
		model.onServiceConnected(null, binder);
		assertThat(model.getBassBoost()).isEqualTo(bassBoost);
	}

	@Test
	public void getSurround_returnSurroundFromService() {
		Surround surround = mock(Surround.class);
		when(service.getSurround()).thenReturn(surround);
		model.onServiceConnected(null, binder);
		assertThat(model.getSurround()).isEqualTo(surround);
	}

	@Test
	public void getAmplifier_returnAmplifierFromService() {
		Amplifier amplifier = mock(Amplifier.class);
		when(service.getAmplifier()).thenReturn(amplifier);
		model.onServiceConnected(null, binder);
		assertThat(model.getAmplifier()).isEqualTo(amplifier);
	}
}
