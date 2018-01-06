package com.edavtyan.materialplayer.lib.file_storage;

import android.content.Context;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class StringFileStorageTest extends BaseTest {
	private class TestStringFileStorage extends StringFileStorage {
		public TestStringFileStorage(Context context) {
			super(context, "test_string");
		}
	}

	private File testDir;
	private TestStringFileStorage fileStorage;

	@Override
	public void beforeEach() {
		super.beforeEach();
		testDir = new File(context.getCacheDir(), "test_string");
		fileStorage = new TestStringFileStorage(context);
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	@Override
	public void afterEach() {
		super.afterEach();
		String[] files = testDir.list();
		for (String file : files) {
			new File(testDir, file).delete();
		}
		testDir.delete();
	}

	@Test
	public void save_and_load_given_string_with_given_filename() {
		fileStorage.save("file100", "abcdefgh");
		assertThat(fileStorage.load("file100")).isEqualTo("abcdefgh");
	}
}
