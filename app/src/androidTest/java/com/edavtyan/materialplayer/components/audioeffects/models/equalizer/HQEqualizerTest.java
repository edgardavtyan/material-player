package com.edavtyan.materialplayer.components.audioeffects.models.equalizer;

import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.lib.BaseTest;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.h6ah4i.android.media.audiofx.IEqualizer;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HQEqualizerTest extends BaseTest {
	private HQEqualizer equalizer;
	private IEqualizer baseEqualizer;
	private AdvancedSharedPrefs prefs;

	@Override public void beforeEach() {
		super.beforeEach();
		baseEqualizer = mock(IEqualizer.class);
		prefs = new AdvancedSharedPrefs(PreferenceManager.getDefaultSharedPreferences(context));
		resetPrefs();

		equalizer = new HQEqualizer(baseEqualizer, prefs);
	}

	@Override public void afterEach() {
		super.afterEach();
		resetPrefs();
	}

	@Test public void constructor_setGainsFromPref() {
		when(baseEqualizer.getNumberOfBands()).thenReturn((short) 4);
		prefs.edit().putString(HQEqualizer.PREF_GAINS, "10,20,30,40").commit();
		equalizer = new HQEqualizer(baseEqualizer, prefs);
		assertThat(equalizer.getGains()).containsExactly(10, 20, 30, 40);
	}

	@Test public void getBandsCount_returnBandsCountFromBaseClass() {
		when(baseEqualizer.getNumberOfBands()).thenReturn((short) 7);
		assertThat(equalizer.getBandsCount()).isEqualTo(7);
	}

	@Test public void getFrequencies_returnFrequenciesFromBaseClass() {
		when(baseEqualizer.getNumberOfBands()).thenReturn((short) 4);
		when(baseEqualizer.getCenterFreq((short) 0)).thenReturn(15000);
		when(baseEqualizer.getCenterFreq((short) 1)).thenReturn(25000);
		when(baseEqualizer.getCenterFreq((short) 2)).thenReturn(35000);
		when(baseEqualizer.getCenterFreq((short) 3)).thenReturn(45000);

		equalizer = new HQEqualizer(baseEqualizer, prefs);

		assertThat(equalizer.getFrequencies()).containsExactly(15, 25, 35, 45);
	}

	@Test
	public void getGains_returnGainsFromPrefs() {
		when(baseEqualizer.getNumberOfBands()).thenReturn((short) 4);
		prefs.edit().putString(HQEqualizer.PREF_GAINS, "30,60,90,120").commit();
		equalizer = new HQEqualizer(baseEqualizer, prefs);
		assertThat(equalizer.getGains()).containsExactly(30, 60, 90, 120);
	}

	@Test public void getGainLimit_returnConvertedGainLimitFromBaseClass() {
		when(baseEqualizer.getBandLevelRange()).thenReturn(new short[]{1500});
		assertThat(equalizer.getGainLimit()).isEqualTo(15);
	}

	@Test public void setBandGain_setConvertedBaseClassBandGain() {
		when(baseEqualizer.getNumberOfBands()).thenReturn((short) 4);
		equalizer = new HQEqualizer(baseEqualizer, prefs);

		equalizer.setBandGain(3, 12);

		verify(baseEqualizer).setBandLevel((short) 3, (short) 1200);
	}

	@Test public void saveSettings_saveGainsToPrefs() {
		when(baseEqualizer.getNumberOfBands()).thenReturn((short) 3);
		equalizer = new HQEqualizer(baseEqualizer, prefs);
		equalizer.setBandGain(0, 3);
		equalizer.setBandGain(1, 5);
		equalizer.setBandGain(2, 8);

		equalizer.saveSettings();

		assertThat(prefs.getString(HQEqualizer.PREF_GAINS, null)).isEqualTo("3,5,8");
	}

	@Test public void isEnabled_returnEnabledStatusFromBaseClass() {
		when(baseEqualizer.getEnabled()).thenReturn(true);
		assertThat(equalizer.isEnabled()).isTrue();
	}

	@Test public void setEnabled_setBaseClassEnabledStatus() {
		equalizer.setEnabled(true);
		verify(baseEqualizer).setEnabled(true);
	}

	@Test public void setEnabled_saveEnabledStatusToPrefs() {
		equalizer.setEnabled(true);
		assertThat(prefs.getBoolean(HQEqualizer.PREF_ENABLED, false)).isTrue();
	}

	private void resetPrefs() {
		prefs.edit()
				.putString(HQEqualizer.PREF_GAINS, null)
				.putBoolean(HQEqualizer.PREF_ENABLED, false)
				.commit();
	}
}
