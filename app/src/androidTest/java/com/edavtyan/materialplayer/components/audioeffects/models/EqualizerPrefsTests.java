package com.edavtyan.materialplayer.components.audioeffects.models;

import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EqualizerPrefsTests extends BaseTest {
	private AdvancedSharedPrefs basePrefs;
	private EqualizerPrefs equalizerPrefs;

	@Override
	public void beforeEach() {
		super.beforeEach();

		basePrefs = new AdvancedSharedPrefs(PreferenceManager.getDefaultSharedPreferences(context));
		equalizerPrefs = new EqualizerPrefs(basePrefs);
	}

	@Override
	public void afterEach() {
		super.afterEach();
		basePrefs.edit().clear().commit();
	}

	@Test
	public void getGains_prefSet_getSetPref() {
		basePrefs.edit().putIntArray("equalizer_gains", new int[]{1, 2, 3, 4, 5}).commit();
		assertThat(equalizerPrefs.getGains(5)).containsExactly(1, 2, 3, 4, 5);
	}

	@Test
	public void getGain_prefNotSet_returnEmptyArrayWithGivenSize() {
		assertThat(equalizerPrefs.getGains(4))
				.hasSize(4)
				.containsExactly(0, 0, 0, 0);
	}

	@Test
	public void getEnabled_prefSet_getSetPref() {
		basePrefs.edit().putBoolean("equalizer_enabled", true).commit();
		assertThat(equalizerPrefs.getEnabled()).isTrue();
	}

	@Test
	public void getEnabled_prefNotSet_getSetPref() {
		assertThat(equalizerPrefs.getEnabled()).isFalse();
	}

	@Test
	public void save_savePrefsInSharedPrefs() {
		equalizerPrefs.save(new int[]{9, 8, 7, 6, 5}, true);
		assertThat(basePrefs.getIntArray("equalizer_gains", 0)).containsExactly(9, 8, 7, 6, 5);
		assertThat(basePrefs.getBoolean("equalizer_enabled", false)).isTrue();
	}
}
