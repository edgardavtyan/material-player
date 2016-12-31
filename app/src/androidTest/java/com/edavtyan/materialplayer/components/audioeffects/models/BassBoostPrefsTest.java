package com.edavtyan.materialplayer.components.audioeffects.models;

import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BassBoostPrefsTest extends BaseTest {
	private AdvancedSharedPrefs basePrefs;
	private BassBoostPrefs bassBoostPrefs;

	@Override
	public void beforeEach() {
		super.beforeEach();
		basePrefs = new AdvancedSharedPrefs(PreferenceManager.getDefaultSharedPreferences(context));
		bassBoostPrefs = new BassBoostPrefs(basePrefs);
	}

	@Override public void afterEach() {
		super.afterEach();
		basePrefs.edit().clear().commit();
	}

	@Test
	public void getStrength_prefSet_returnSetPrefValue() {
		basePrefs.edit().putInt("bassBoost_strength", 300).commit();
		assertThat(bassBoostPrefs.getStrength()).isEqualTo(300);
	}

	@Test
	public void getStrength_prefNotSet_returnDefaultValue() {
		assertThat(bassBoostPrefs.getStrength()).isEqualTo(0);
	}

	@Test
	public void save_saveGivenValuesInSharedPrefs() {
		bassBoostPrefs.save(400);
		assertThat(basePrefs.getInt("bassBoost_strength", 0)).isEqualTo(400);
	}
}
