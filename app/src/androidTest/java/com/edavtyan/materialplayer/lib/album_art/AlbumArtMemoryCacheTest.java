package com.edavtyan.materialplayer.lib.album_art;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AlbumArtMemoryCacheTest extends BaseTest {
	private AlbumArtMemoryCache memoryCache;

	@Override
	public void beforeEach() {
		super.beforeEach();
		memoryCache = new AlbumArtMemoryCache();
	}

	@Test
	public void put_storeValueInCache() {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		memoryCache.put(1, art);
		assertThat(memoryCache.get(1)).isSameAs(art);
	}

	@Test
	public void exists_valueExists_returnTrue() {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		memoryCache.put(2, art);
		assertThat(memoryCache.contains(2)).isTrue();
	}

	@Test
	public void exists_valueNotExists_returnFalse() {
		assertThat(memoryCache.contains(3)).isFalse();
	}
}
