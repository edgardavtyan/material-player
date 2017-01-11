package com.edavtyan.materialplayer.components.audioeffects.models;

import android.media.audiofx.Equalizer;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StandardEqualizerTest extends BaseTest {
	private AdvancedSharedPrefs basePrefs;
	private EqualizerPrefs prefs;
	private Equalizer baseEqualizer;
	private StandardEqualizer equalizer;

	@Override
	public void beforeEach() {
		super.beforeEach();

		basePrefs = new AdvancedSharedPrefs(PreferenceManager.getDefaultSharedPreferences(context));
		prefs = spy(new EqualizerPrefs(basePrefs));
		baseEqualizer = new Equalizer(0, 0);
		equalizer = new StandardEqualizer(baseEqualizer, prefs);
	}

	@Override
	public void afterEach() {
		super.afterEach();
		basePrefs.edit().clear().commit();
	}

	@Test
	public void constructor_initFrequencies() {
		int[] frequencies = new int[]{14000, 3600, 910, 230, 60};
		assertThat(equalizer.getFrequencies()).containsExactly(frequencies);
	}

	@Test
	public void constructor_gainsNotPreviouslySet_initDefaultGains() {
		assertThat(equalizer.getGains()).containsExactly(0, 0, 0, 0, 0);
	}

	@Test
	public void constructor_gainsPreviouslySet_initGainsFromPrefs() {
		when(prefs.getGains(anyInt())).thenReturn(new int[]{1, 2, 3, 4, 5});
		equalizer = new StandardEqualizer(baseEqualizer, prefs);
		assertThat(equalizer.getGains()).containsExactly(1, 2, 3, 4, 5);
	}

	@Test
	public void getBandsCount_correctBandsCount() {
		assertThat(equalizer.getBandsCount()).isEqualTo(5);
	}

	@Test
	public void set_band_gain_in_reverse_in_millibel() {
		equalizer.setBandGain(0, 5);
		assertThat(baseEqualizer.getBandLevel((short) 4)).isEqualTo((short) 500);
	}

	@Test
	public void save_settings() {
		equalizer.setBandGain(0, 0);
		equalizer.setBandGain(1, 1);
		equalizer.setBandGain(2, 2);
		equalizer.setBandGain(3, 3);
		equalizer.setBandGain(4, 4);
		equalizer.setEnabled(true);

		equalizer.saveSettings();

		verify(prefs).save(new int[]{0, 1, 2, 3, 4}, true);
	}
}
