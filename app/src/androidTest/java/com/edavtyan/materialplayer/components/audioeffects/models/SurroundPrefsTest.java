package com.edavtyan.materialplayer.components.audioeffects.models;

import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SurroundPrefsTest extends BaseTest {
	private AdvancedSharedPrefs basePrefs;
	private SurroundPrefs surroundPrefs;

	@Override
	public void beforeEach() {
		super.beforeEach();
		basePrefs = new AdvancedSharedPrefs(PreferenceManager.getDefaultSharedPreferences(context));
		surroundPrefs = new SurroundPrefs(basePrefs);
	}

	@Override
	public void afterEach() {
		super.afterEach();
		basePrefs.edit().clear().commit();
	}

	@Test
	public void getStrength_prefSet_returnSetPrefValue() {
		basePrefs.edit().putInt("surround_strength", 100).commit();
		assertThat(surroundPrefs.getStrength()).isEqualTo(100);
	}

	@Test
	public void getStrength_prefNotSet_returnDefaultValue() {
		assertThat(surroundPrefs.getStrength()).isEqualTo(0);
	}

	@Test
	public void save_saveGivenValuesInSharedPrefs() {
		surroundPrefs.save(200);
		assertThat(basePrefs.getInt("surround_strength", 0)).isEqualTo(200);
	}
}
