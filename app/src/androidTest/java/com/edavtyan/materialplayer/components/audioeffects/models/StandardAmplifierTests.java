package com.edavtyan.materialplayer.components.audioeffects.models;

import android.media.audiofx.LoudnessEnhancer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.test.filters.SdkSuppress;

import com.edavtyan.materialplayer.components.audioeffects.amplifier.AmplifierPrefs;
import com.edavtyan.materialplayer.components.audioeffects.amplifier.StandardAmplifier;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
@SdkSuppress(minSdkVersion = Build.VERSION_CODES.KITKAT)
public class StandardAmplifierTests extends BaseTest {
	private StandardAmplifier amplifier;
	private AmplifierPrefs prefs;
	private LoudnessEnhancer baseAmplifier;

	@Override
	public void beforeEach() {
		super.beforeEach();
		baseAmplifier = new LoudnessEnhancer(0);
		prefs = mock(AmplifierPrefs.class);
		amplifier = new StandardAmplifier(baseAmplifier, prefs);
	}

	@Test
	public void constructor_setEnabledToTrue() {
		assertThat(baseAmplifier.getEnabled()).isTrue();
	}

	@Test
	public void constructor_setGainFromPrefs() {
		when(prefs.getGain()).thenReturn(5);
		amplifier = new StandardAmplifier(baseAmplifier, prefs);
		assertThat(baseAmplifier.getTargetGain()).isEqualTo(500);
	}

	@Test
	public void getGain_returnGainInDeciBel() {
		baseAmplifier.setTargetGain(1000);
		assertThat(amplifier.getGain()).isEqualTo(10);
	}

	@Test
	public void setGain_gainInDeciBel_setBaseAmplifierGainInMilliBel() {
		amplifier.setGain(15);
		assertThat(baseAmplifier.getTargetGain()).isEqualTo(1500);
	}

	@Test
	public void saveSettings_callPrefs() {
		amplifier.setGain(15);
		amplifier.saveSettings();
		verify(prefs).save(15);
	}
}
