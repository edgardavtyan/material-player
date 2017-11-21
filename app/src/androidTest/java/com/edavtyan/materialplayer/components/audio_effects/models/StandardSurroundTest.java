package com.edavtyan.materialplayer.components.audio_effects.models;

import android.media.audiofx.Virtualizer;

import com.edavtyan.materialplayer.components.audio_effects.surround.StandardSurround;
import com.edavtyan.materialplayer.components.audio_effects.surround.SurroundPrefs;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StandardSurroundTest extends BaseTest {
	private SurroundPrefs prefs;
	private Virtualizer baseSurround;
	private StandardSurround surround;

	@Override
	public void beforeEach() {
		super.beforeEach();
		baseSurround = new Virtualizer(0, 0);
		prefs = mock(SurroundPrefs.class);
		surround = new StandardSurround(baseSurround, prefs);
	}

	@Test
	public void init_strength_from_prefs() {
		when(prefs.getStrength()).thenReturn(10);
		surround = new StandardSurround(baseSurround, prefs);
		assertThat(surround.getStrength()).isEqualTo(10);
	}

	@Test
	public void return_predefined_max_strength() {
		assertThat(surround.getMaxStrength()).isEqualTo(1000);
	}

	@Test
	public void set_base_surround_strength() {
		surround.setStrength(20);
		assertThat(baseSurround.getRoundedStrength()).isEqualTo((short) 20);
	}

	@Test
	public void get_base_surround_strength() {
		baseSurround.setStrength((short) 30);
		assertThat(surround.getStrength()).isEqualTo(30);
	}

	@Test
	public void save_settings() {
		surround.setStrength(40);
		surround.saveSettings();
		verify(prefs).save(40);
	}
}
