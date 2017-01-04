package com.edavtyan.materialplayer.components.audioeffects.models;

import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AmplifierPrefsTests extends BaseTest {
	private AmplifierPrefs prefs;
	private AdvancedSharedPrefs basePrefs;

	@Override
	public void beforeEach() {
		super.beforeEach();
		basePrefs = new AdvancedSharedPrefs(PreferenceManager.getDefaultSharedPreferences(context));
		prefs = new AmplifierPrefs(basePrefs);
	}

	@Override
	public void afterEach() {
		super.afterEach();
		basePrefs.edit().clear().commit();
	}

	@Test
	public void getGain_valueSet_returnValueFromSharedPrefs() {
		basePrefs.edit().putInt("amplifier_gain", 10).commit();
		assertThat(prefs.getGain()).isEqualTo(10);
	}

	@Test
	public void getGain_valueNotSet_returnDefaultValue() {
		assertThat(prefs.getGain()).isZero();
	}

	@Test
	public void save_saveGainToSharedPrefs() {
		prefs.save(20);
		assertThat(basePrefs.getInt("amplifier_gain", 0)).isEqualTo(20);
	}
}
