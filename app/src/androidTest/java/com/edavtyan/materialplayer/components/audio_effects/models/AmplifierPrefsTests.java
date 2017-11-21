package com.edavtyan.materialplayer.components.audio_effects.models;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.components.audio_effects.amplifier.AmplifierPrefs;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AmplifierPrefsTests extends BaseTest {
	private SharedPreferences basePrefs;
	private AdvancedSharedPrefs advancedPrefs;
	private AmplifierPrefs prefs;

	@Override
	public void beforeEach() {
		super.beforeEach();
		basePrefs = PreferenceManager.getDefaultSharedPreferences(context);
		advancedPrefs = new AdvancedSharedPrefs(basePrefs);
		prefs = new AmplifierPrefs(advancedPrefs);
	}

	@Override
	public void afterEach() {
		super.afterEach();
		basePrefs.edit().clear().commit();
	}

	@Test
	public void getGain_valueSet_returnValueFromSharedPrefs() {
		advancedPrefs.edit().putInt("amplifier_gain", 10).commit();
		assertThat(prefs.getGain()).isEqualTo(10);
	}

	@Test
	public void getGain_valueNotSet_returnDefaultValue() {
		assertThat(prefs.getGain()).isZero();
	}

	@Test
	public void save_saveGainToSharedPrefs() {
		prefs.save(20);
		assertThat(advancedPrefs.getInt("amplifier_gain", 0)).isEqualTo(20);
	}
}
