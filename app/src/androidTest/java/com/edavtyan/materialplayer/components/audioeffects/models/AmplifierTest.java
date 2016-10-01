package com.edavtyan.materialplayer.components.audioeffects.models;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.lib.BaseTest;
import com.h6ah4i.android.media.audiofx.IPreAmp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AmplifierTest extends BaseTest {
	private Amplifier amplifier;
	private IPreAmp preAmp;
	private SharedPreferences prefs;

	@Override
	public void beforeEach() {
		super.beforeEach();
		preAmp = mock(IPreAmp.class);
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		amplifier = spy(new Amplifier(preAmp, prefs));
	}

	@Test
	@SuppressLint("CommitPrefEdits")
	public void constructor_strengthPreviouslySet_setStrengthFromPrefs() {
		reset(preAmp);
		prefs.edit().putInt(Amplifier.PREF_STRENGTH, 25).commit();
		amplifier = new Amplifier(preAmp, prefs);
		verify(preAmp).setLevel(1.25f);
	}

	@Test
	public void getMaxStrength_returnMaxAmplifierStrength() {
		assertThat(amplifier.getMaxStrength()).isEqualTo(Amplifier.MAX_STRENGTH);
	}

	@Test
	public void getStrength_returnConvertedStrength() {
		when(preAmp.getLevel()).thenReturn(1.4f);
		assertThat(amplifier.getStrength()).isEqualTo(40);
	}

	@Test
	public void setStrength_setConvertedStrength() {
		amplifier.setStrength(35);
		verify(preAmp).setLevel(1.35f);
	}

	@Test
	public void saveSettings_saveStrengthToPrefs() {
		doReturn(25).when(amplifier).getStrength();
		amplifier.saveSettings();
		assertThat(prefs.getInt(Amplifier.PREF_STRENGTH, Amplifier.DEFAULT_STRENGTH)).isEqualTo(25);
	}
}
