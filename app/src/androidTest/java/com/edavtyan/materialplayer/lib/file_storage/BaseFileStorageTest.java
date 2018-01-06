package com.edavtyan.materialplayer.lib.file_storage;

import android.content.Context;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BaseFileStorageTest extends BaseTest {
	public static class TestBaseFileStorage extends BaseFileStorage {
		public TestBaseFileStorage(Context context) {
			super(context, "test");
		}
	}

	private File testDir;
	private TestBaseFileStorage fileStorage;

	@Override
	public void beforeEach() {
		super.beforeEach();
		testDir = new File(context.getCacheDir(), "test");
		fileStorage = new TestBaseFileStorage(context);
	}

	@Override
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public void afterEach() {
		super.afterEach();

		String[] files = testDir.list();
		if (files != null) {
			for (String file : files) {
				new File(testDir, file).delete();
			}
		}
		testDir.delete();
	}

	@Test
	public void create_given_dir() {
		assertThat(testDir.exists()).isTrue();
	}

	@Test
	public void save_and_load_bytes_to_correct_file() {
		byte[] bytes = new byte[]{1, 2, 3, 4, 5};
		fileStorage.saveBytes("file100", bytes);
		assertThat(fileStorage.loadBytes("file100")).isEqualTo(bytes);
		assertThat(new File(testDir, "file100").exists()).isTrue();
	}

	@Test
	public void throw_runtime_exception_when_saving_null() {
		assertThatThrownBy(() -> fileStorage.saveBytes("file200", null))
				.isInstanceOf(RuntimeException.class);
	}

	@Test
	public void throw_runtime_exception_when_loading_non_existing_file() {
		assertThatThrownBy(() -> fileStorage.loadBytes("not_exists"))
				.isInstanceOf(RuntimeException.class);
	}

	@Test
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public void return_true_when_checking_if_existing_file_exists() throws IOException {
		new File(testDir, "file300").createNewFile();
		assertThat(fileStorage.exists("file300")).isTrue();
	}
}
