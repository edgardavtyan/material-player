package com.edavtyan.materialplayer.lib.file_storage;

import android.os.Environment;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class StringFileStorageTest extends BaseTest {
	private static final File TEST_DIR = new File(Environment.getExternalStorageDirectory(), "MaterialPlayer/test_string/");

	public static class TestStringFileStorage extends StringFileStorage {
		public TestStringFileStorage() {
			super("test_string");
		}
	}

	private TestStringFileStorage fileStorage;

	@Override
	public void beforeEach() {
		super.beforeEach();
		fileStorage = new TestStringFileStorage();
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	@Override
	public void afterEach() {
		super.afterEach();
		String[] files = TEST_DIR.list();
		for (String file : files) {
			new File(TEST_DIR, file).delete();
		}
		TEST_DIR.delete();
	}

	@Test
	public void save_and_load_given_string_with_given_filename() {
		fileStorage.save("file100", "abcdefgh");
		assertThat(fileStorage.load("file100")).isEqualTo("abcdefgh");
	}
}
