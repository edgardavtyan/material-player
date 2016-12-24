package com.edavtyan.materialplayer.components.audioeffects.models;

import android.media.audiofx.Equalizer;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StandardEqualizerTest extends BaseTest {
	private AdvancedSharedPrefs prefs;
	private Equalizer baseEqualizer;
	private StandardEqualizer equalizer;

	@Override public void beforeEach() {
		super.beforeEach();

		prefs = new AdvancedSharedPrefs(PreferenceManager.getDefaultSharedPreferences(context));
		baseEqualizer = new Equalizer(0, 0);
		equalizer = new StandardEqualizer(baseEqualizer, prefs);
	}

	@Override public void afterEach() {
		super.afterEach();
		prefs.edit().clear().commit();
	}

	@Test public void constructor_initFrequencies() {
		int[] frequencies = new int[]{60, 230, 910, 3600, 14000};
		assertThat(equalizer.getFrequencies()).containsExactly(frequencies);
	}

	@Test public void constructor_gainsNotPreviouslySet_initDefaultGains() {
		assertThat(equalizer.getGains()).containsExactly(0, 0, 0, 0, 0);
	}

	@Test public void constructor_gainsPreviouslySet_initGainsFromPrefs() {
		prefs.edit().putIntArray(StandardEqualizer.PREF_GAINS, new int[]{1,2,3,4,5}).commit();
		equalizer = new StandardEqualizer(baseEqualizer, prefs);
		assertThat(equalizer.getGains()).containsExactly(1,2,3,4,5);
	}

	@Test public void getBandsCount_correctBandsCount() {
		assertThat(equalizer.getBandsCount()).isEqualTo(5);
	}
}
