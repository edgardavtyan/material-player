package com.edavtyan.materialplayer.components.audioeffects.models;

import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.lib.BaseTest;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.h6ah4i.android.media.audiofx.IVirtualizer;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SurroundTest extends BaseTest {
	private Surround surround;
	private IVirtualizer baseSurround;
	private AdvancedSharedPrefs prefs;

	@Override public void beforeEach() {
		super.beforeEach();
		baseSurround = mock(IVirtualizer.class);
		prefs = new AdvancedSharedPrefs(PreferenceManager.getDefaultSharedPreferences(context));
		surround = spy(new Surround(baseSurround, prefs));
	}

	@Test public void constructor_setStrengthFromPrefs() {
		reset(baseSurround);
		prefs.edit().putInt(Surround.PREF_STRENGTH, 125).commit();
		surround = new Surround(baseSurround, prefs);
		verify(baseSurround).setStrength((short) 125);
	}

	@Test public void getMaxStrength_returnConstantMaxStrength() {
		assertThat(surround.getMaxStrength()).isEqualTo(Surround.MAX_STRENGTH);
	}

	@Test public void getStrength_returnStrengthFromBaseClass() {
		when(baseSurround.getRoundedStrength()).thenReturn((short) 135);
		assertThat(surround.getStrength()).isEqualTo(135);
	}

	@Test public void setStrength_setBaseClassStrength() {
		surround.setStrength(145);
		verify(baseSurround).setStrength((short) 145);
	}

	@Test public void saveSettings_saveStrengthToPrefs() {
		doReturn(155).when(surround).getStrength();
		surround.saveSettings();
		assertThat(prefs.getInt(Surround.PREF_STRENGTH, Surround.DEFAULT_STRENGTH)).isEqualTo(155);
	}
}
