package com.edavtyan.materialplayer.components.audioeffects.models;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.components.audioeffects.equalizer.EqualizerPrefs;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EqualizerPrefsTests extends BaseTest {
	private SharedPreferences basePrefs;
	private AdvancedSharedPrefs advancedPrefs;
	private EqualizerPrefs prefs;

	@Override
	public void beforeEach() {
		super.beforeEach();
		basePrefs = PreferenceManager.getDefaultSharedPreferences(context);
		advancedPrefs = new AdvancedSharedPrefs(basePrefs);
		prefs = new EqualizerPrefs(advancedPrefs);
	}

	@Override
	public void afterEach() {
		super.afterEach();
		basePrefs.edit().clear().commit();
	}

	@Test
	public void getGains_prefSet_getSetPref() {
		advancedPrefs.edit().putIntArray("equalizer_gains", new int[]{1, 2, 3, 4, 5}).commit();
		assertThat(prefs.getGains(5)).containsExactly(1, 2, 3, 4, 5);
	}

	@Test
	public void getGain_prefNotSet_returnEmptyArrayWithGivenSize() {
		assertThat(prefs.getGains(4))
				.hasSize(4)
				.containsExactly(0, 0, 0, 0);
	}

	@Test
	public void getEnabled_prefSet_getSetPref() {
		advancedPrefs.edit().putBoolean("equalizer_enabled", true).commit();
		assertThat(prefs.getEnabled()).isTrue();
	}

	@Test
	public void getEnabled_prefNotSet_getSetPref() {
		assertThat(prefs.getEnabled()).isFalse();
	}

	@Test
	public void save_savePrefsInSharedPrefs() {
		prefs.save(new int[]{9, 8, 7, 6, 5}, true);
		assertThat(advancedPrefs.getIntArray("equalizer_gains", 0)).containsExactly(9, 8, 7, 6, 5);
		assertThat(advancedPrefs.getBoolean("equalizer_enabled", false)).isTrue();
	}
}
