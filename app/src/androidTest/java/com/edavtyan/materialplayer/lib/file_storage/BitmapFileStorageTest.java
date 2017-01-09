package com.edavtyan.materialplayer.lib.file_storage;

import android.graphics.Bitmap;
import android.os.Environment;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class BitmapFileStorageTest extends BaseTest {
	private static final File TEST_DIR = new File(Environment.getExternalStorageDirectory(), "MaterialPlayer/test_bitmap/");

	public static class TestBitmapFileStorage extends BitmapFileStorage {
		public TestBitmapFileStorage() {
			super("test_bitmap");
		}
	}

	private TestBitmapFileStorage fileStorage;

	@Override
	public void beforeEach() {
		super.beforeEach();
		fileStorage = new TestBitmapFileStorage();
	}

	@Override
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public void afterEach() {
		super.afterEach();
		String[] files = TEST_DIR.list();
		for (String file : files) {
			new File(TEST_DIR, file).delete();
		}
		TEST_DIR.delete();
	}

	@Test
	public void save_and_load_given_bitmap_with_given_filename() {
		Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		fileStorage.save("file100", bitmap);
		assertThat(fileStorage.load("file100").sameAs(bitmap)).isTrue();
	}
}
