package com.edavtyan.materialplayer.player;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;
import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerPrefsTest extends BaseTest {
	private SharedPreferences basePrefs;
	private AdvancedSharedPrefs advancedPrefs;
	private PlayerPrefs playerPrefs;

	@Override
	public void beforeEach() {
		super.beforeEach();
		basePrefs = PreferenceManager.getDefaultSharedPreferences(context);
		advancedPrefs = new AdvancedSharedPrefs(basePrefs);
		playerPrefs = new PlayerPrefs(advancedPrefs);
	}

	@Override
	public void afterEach() {
		super.afterEach();
		basePrefs.edit().clear().commit();
	}

	@Test
	public void getShuffleMode_prefSet_returnSetPrefValue() {
		advancedPrefs.edit().putEnum("player_shuffle", ShuffleMode.ENABLED).commit();
		assertThat(playerPrefs.getShuffleMode()).isEqualTo(ShuffleMode.ENABLED);
	}

	@Test
	public void getShuffleMode_prefNotSet_returnDefaultValue() {
		assertThat(playerPrefs.getShuffleMode()).isEqualTo(ShuffleMode.DISABLED);
	}

	@Test
	public void saveShuffleMode_saveGivenValueInSharedPrefs() {
		playerPrefs.saveShuffleMode(ShuffleMode.ENABLED);
		assertThat(advancedPrefs.getEnum("player_shuffle", ShuffleMode.DISABLED))
				.isEqualTo(ShuffleMode.ENABLED);
	}

	@Test
	public void getRepeatMode_prefSet_returnSetPrefValue() {
		advancedPrefs.edit().putEnum("player_repeat", RepeatMode.REPEAT_ALL).commit();
		assertThat(playerPrefs.getRepeatMode()).isEqualTo(RepeatMode.REPEAT_ALL);
	}

	@Test
	public void getRepeatMode_prefNotSet_returnDefaultValue() {
		assertThat(playerPrefs.getRepeatMode()).isEqualTo(RepeatMode.DISABLED);
	}

	@Test
	public void saveRepeatMode_saveGivenValueInSharedPrefs() {
		playerPrefs.saveRepeatMode(RepeatMode.REPEAT_ALL);
		assertThat(advancedPrefs.getEnum("player_repeat", RepeatMode.DISABLED))
				.isEqualTo(RepeatMode.REPEAT_ALL);
	}

	@Test
	public void getCurrentIndex_prefSet_returnSetPrefValue() {
		advancedPrefs.edit().putInt("player_position", 800).apply();
		assertThat(playerPrefs.getCurrentIndex()).isEqualTo(800);
	}

	@Test
	public void getCurrentIndex_prefNotSet_returnDefaultValue() {
		assertThat(playerPrefs.getCurrentIndex()).isEqualTo(0);
	}

	@Test
	public void saveCurrentIndex_saveGivenValueToPrefs() {
		playerPrefs.saveCurrentIndex(1000);
		assertThat(playerPrefs.getCurrentIndex()).isEqualTo(1000);
	}
}
