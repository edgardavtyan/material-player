package com.edavtyan.materialplayer.components.audioeffects.models;

import android.annotation.SuppressLint;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.lib.BaseTest;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.h6ah4i.android.media.audiofx.IBassBoost;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BassBoostTest extends BaseTest {
	private BassBoost bassBoost;
	private IBassBoost baseBassBoost;
	private AdvancedSharedPrefs prefs;

	@Override
	public void beforeEach() {
		super.beforeEach();
		baseBassBoost = mock(IBassBoost.class);
		prefs = new AdvancedSharedPrefs(PreferenceManager.getDefaultSharedPreferences(context));
		bassBoost = spy(new BassBoost(baseBassBoost, prefs));
		reset(baseBassBoost);
	}

	@Test
	@SuppressLint("CommitPrefEdits")
	public void constructor_setStrengthFromPrefs() {
		reset(baseBassBoost);
		prefs.edit().putInt(BassBoost.PREF_STRENGTH, 45).commit();
		new BassBoost(baseBassBoost, prefs);
		verify(baseBassBoost).setStrength((short) 45);
	}

	@Test
	public void getMaxStrength_returnConstantMaxStrength() {
		assertThat(bassBoost.getMaxStrength()).isEqualTo(BassBoost.MAX_STRENGTH);
	}

	@Test
	public void getStrength_returnStrengthFromBaseClass() {
		when(baseBassBoost.getRoundedStrength()).thenReturn((short) 55);
		assertThat(bassBoost.getStrength()).isEqualTo(55);
	}

	@Test
	public void setStrength_setBaseClassStrength() {
		bassBoost.setStrength(65);
		verify(baseBassBoost).setStrength((short) 65);
	}

	@Test
	public void saveSettings_saveStrengthToPrefs() {
		doReturn(65).when(bassBoost).getStrength();
		bassBoost.saveSettings();
		assertThat(prefs.getInt(BassBoost.PREF_STRENGTH, BassBoost.DEFAULT_STRENGTH)).isEqualTo(65);
	}
}
