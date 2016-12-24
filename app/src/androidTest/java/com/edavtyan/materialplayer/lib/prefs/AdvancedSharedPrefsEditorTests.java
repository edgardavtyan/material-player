package com.edavtyan.materialplayer.lib.prefs;

import android.content.SharedPreferences;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AdvancedSharedPrefsEditorTests extends BaseTest {
	private AdvancedSharedPrefsEditor editor;
	private SharedPreferences.Editor baseEditor;

	@Override public void beforeEach() {
		super.beforeEach();
		baseEditor = mock(SharedPreferences.Editor.class);
		editor = new AdvancedSharedPrefsEditor(baseEditor);
	}

	@Test public void apply_applyBaseEditor() {
		editor.apply();
		verify(baseEditor).apply();
	}

	@Test public void commit_commitBaseEditor() {
		editor.commit();
		verify(baseEditor).commit();
	}

	@Test public void clear_clearBaseEditor() {
		editor.clear();
		verify(baseEditor).clear();
	}

	@Test public void clean_returnItself() {
		assertThat(editor.clear()).isSameAs(editor);
	}

	@Test public void putInt_callBaseEditor() {
		editor.putInt("key", 1);
		verify(baseEditor).putInt("key", 1);
	}

	@Test public void putInt_returnItself() {
		assertThat(editor.putInt("key", 1)).isSameAs(editor);
	}

	@Test public void putString_callBaseEditor() {
		editor.putString("key", "str");
		verify(baseEditor).putString("key", "str");
	}

	@Test public void putString_returnItself() {
		assertThat(editor.putString("key", "str")).isSameAs(editor);
	}

	@Test public void putBoolean_callBaseEditor() {
		editor.putBoolean("key", true);
		verify(baseEditor).putBoolean("key", true);
	}

	@Test public void putBoolean_returnItself() {
		assertThat(editor.putBoolean("key", true)).isSameAs(editor);
	}

	@Test public void putIntArray_callBaseEditor() {
		editor.putIntArray("key_intArray", new int[]{1, 2, 3, 4, 5});
		verify(baseEditor).putString("key_intArray", "1,2,3,4,5");
	}
}
