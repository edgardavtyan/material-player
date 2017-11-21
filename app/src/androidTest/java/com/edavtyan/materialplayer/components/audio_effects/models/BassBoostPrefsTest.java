package com.edavtyan.materialplayer.components.audio_effects.models;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.components.audio_effects.bassboost.BassBoostPrefs;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BassBoostPrefsTest extends BaseTest {
	private SharedPreferences basePrefs;
	private AdvancedSharedPrefs advancedPrefs;
	private BassBoostPrefs prefs;

	@Override
	public void beforeEach() {
		super.beforeEach();
		basePrefs = PreferenceManager.getDefaultSharedPreferences(context);
		advancedPrefs = new AdvancedSharedPrefs(basePrefs);
		prefs = new BassBoostPrefs(advancedPrefs);
	}

	@Override
	public void afterEach() {
		super.afterEach();
		basePrefs.edit().clear().commit();
	}

	@Test
	public void getStrength_prefSet_returnSetPrefValue() {
		advancedPrefs.edit().putInt("bassBoost_strength", 300).commit();
		assertThat(prefs.getStrength()).isEqualTo(300);
	}

	@Test
	public void getStrength_prefNotSet_returnDefaultValue() {
		assertThat(prefs.getStrength()).isEqualTo(0);
	}

	@Test
	public void save_saveGivenValuesInSharedPrefs() {
		prefs.save(400);
		assertThat(advancedPrefs.getInt("bassBoost_strength", 0)).isEqualTo(400);
	}
}
