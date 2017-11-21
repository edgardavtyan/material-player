package com.edavtyan.materialplayer.components.audioeffects.models;

import android.content.SharedPreferences;
import android.media.audiofx.BassBoost;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.components.audioeffects.bassboost.BassBoostPrefs;
import com.edavtyan.materialplayer.components.audioeffects.bassboost.StandardBassBoost;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StandardBassBoostTest extends BaseTest {
	private SharedPreferences basePrefs;
	private BassBoostPrefs prefs;
	private BassBoost baseBassBoost;
	private StandardBassBoost bassBoost;

	@Override
	public void beforeEach() {
		super.beforeEach();
		basePrefs = PreferenceManager.getDefaultSharedPreferences(context);
		prefs = new BassBoostPrefs(new AdvancedSharedPrefs(basePrefs));
		baseBassBoost = new BassBoost(0, 0);
		bassBoost = new StandardBassBoost(baseBassBoost, prefs);
	}

	@Override
	public void afterEach() {
		super.afterEach();
		basePrefs.edit().clear().commit();
	}

	@Test
	public void constructor_enableBaseBassBoost() {
		bassBoost = new StandardBassBoost(baseBassBoost, prefs);
		assertThat(baseBassBoost.getEnabled()).isTrue();
	}

	@Test
	public void constructor_setBassBoostStrengthFromSettings() {
		prefs.save(123);
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
		assertThat(prefs.getStrength()).isEqualTo(456);
	}
}
