package com.edavtyan.materialplayer.components.audioeffects.models;

import android.media.audiofx.BassBoost;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StandardBassBoostTest extends BaseTest {
	private BassBoost baseBassBoost;
	private AdvancedSharedPrefs prefs;
	private StandardBassBoost bassBoost;

	@Override public void beforeEach() {
		super.beforeEach();
		baseBassBoost = new BassBoost(0, 0);
		prefs = new AdvancedSharedPrefs(PreferenceManager.getDefaultSharedPreferences(context));
		bassBoost = new StandardBassBoost(baseBassBoost, prefs);
	}

	@Test
	public void constructor_enableBaseBassBoost() {
		bassBoost = new StandardBassBoost(baseBassBoost, prefs);
		assertThat(baseBassBoost.getEnabled()).isTrue();
	}

	@Test
	public void constructor_setBassBoostStrengthFromSettings() {
		prefs.edit().putInt(StandardBassBoost.PREF_STRENGTH, 123).apply();
		bassBoost = new StandardBassBoost(baseBassBoost, prefs);
		assertThat(baseBassBoost.getRoundedStrength()).isEqualTo((short) 123);
		assertThat(bassBoost.getStrength()).isEqualTo(123);
	}

	@Test
	public void getMaxStrength_correctStrength() {
		assertThat(bassBoost.getMaxStrength()).isEqualTo(1000);
	}

	@Test
	public void getStrength_baseStrength() {
		baseBassBoost.setStrength((short) 234);
	}

	@Test
	public void setStrength_setBaseStrength() {
		bassBoost.setStrength(345);
		assertThat(baseBassBoost.getRoundedStrength()).isEqualTo((short) 345);
	}

	@Test
	public void saveSettings_saveStrengthToPrefs() {
		bassBoost.setStrength(456);
		bassBoost.saveSettings();
		assertThat(prefs.getInt(StandardBassBoost.PREF_STRENGTH, 0)).isEqualTo(456);
	}
}
