package com.edavtyan.materialplayer.components.audioeffects.models;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.components.audioeffects.surround.SurroundPrefs;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SurroundPrefsTest extends BaseTest {
	private SharedPreferences basePrefs;
	private AdvancedSharedPrefs advancedPrefs;
	private SurroundPrefs surroundPrefs;

	@Override
	public void beforeEach() {
		super.beforeEach();
		basePrefs = PreferenceManager.getDefaultSharedPreferences(context);
		advancedPrefs = new AdvancedSharedPrefs(basePrefs);
		surroundPrefs = new SurroundPrefs(advancedPrefs);
	}

	@Override
	public void afterEach() {
		super.afterEach();
		basePrefs.edit().clear().commit();
	}

	@Test
	public void getStrength_prefSet_returnSetPrefValue() {
		advancedPrefs.edit().putInt("surround_strength", 100).commit();
		assertThat(surroundPrefs.getStrength()).isEqualTo(100);
	}

	@Test
	public void getStrength_prefNotSet_returnDefaultValue() {
		assertThat(surroundPrefs.getStrength()).isEqualTo(0);
	}

	@Test
	public void save_saveGivenValuesInSharedPrefs() {
		surroundPrefs.save(200);
		assertThat(advancedPrefs.getInt("surround_strength", 0)).isEqualTo(200);
	}
}
