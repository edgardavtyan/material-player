package com.edavtyan.materialplayer.lib.prefs;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SuppressLint("CommitPrefEdits")
public class AdvancedSharedPrefsTest extends BaseTest {
	private AdvancedSharedPrefs prefs;
	private SharedPreferences basePrefs;

	private enum TestEnum {
		FIRST, SECOND, THIRD
	}

	@Override public void beforeEach() {
		super.beforeEach();
		basePrefs = PreferenceManager.getDefaultSharedPreferences(context);
		prefs = new AdvancedSharedPrefs(basePrefs);
	}

	@Override public void afterEach() {
		super.afterEach();
		basePrefs.edit().clear().commit();
	}

	@Test public void getEnum_prefSaved_returnEnumFromString() {
		basePrefs.edit().putString("key_enum", TestEnum.FIRST.name()).commit();
		assertThat(prefs.getEnum("key_enum", TestEnum.SECOND)).isEqualTo(TestEnum.FIRST);
	}

	@Test public void getEnum_prefNotSaved_returnDefaultEnumFromString() {
		assertThat(prefs.getEnum("key_enum", TestEnum.THIRD)).isEqualTo(TestEnum.THIRD);
	}

	@Test public void getInt_prefSaved_returnSavedPref() {
		basePrefs.edit().putInt("key_int", 2).commit();
		assertThat(prefs.getInt("key_int", 1)).isEqualTo(2);
	}

	@Test public void getInt_prefNotSaved_returnDefaultPref() {
		assertThat(prefs.getInt("key_int", 3)).isEqualTo(3);
	}

	@Test public void getString_prefSaved_returnSavedPref() {
		basePrefs.edit().putString("key_string", "second").commit();
		assertThat(prefs.getString("key_string", "first")).isEqualTo("second");
	}

	@Test public void getString_prefNotSaved_returnDefaultPref() {
		assertThat(prefs.getString("key_string", "third")).isEqualTo("third");
	}

	@Test public void getBoolean_prefSaved_returnSavedPref() {
		basePrefs.edit().putBoolean("key_boolean", true).commit();
		assertThat(prefs.getBoolean("key_boolean", false)).isTrue();
	}

	@Test public void getBoolean_prefNotSaved_returnDefaultPref() {
		assertThat(prefs.getBoolean("key_boolean", false)).isFalse();
	}

	@Test public void registerOnSharedPreferencesChangedListener_callBasePrefs()
	throws InterruptedException {
		OnSharedPreferenceChangeListener listener = mock(OnSharedPreferenceChangeListener.class);
		prefs.registerOnSharedPreferenceChangeListener(listener);

		basePrefs.edit().putString("key", "value").apply();
		Thread.sleep(100);

		verify(listener).onSharedPreferenceChanged(basePrefs, "key");
	}
}
