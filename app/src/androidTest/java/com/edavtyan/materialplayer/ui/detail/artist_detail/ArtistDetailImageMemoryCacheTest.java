package com.edavtyan.materialplayer.ui.detail.artist_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.testlib.tests.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArtistDetailImageMemoryCacheTest extends BaseTest {
	private ArtistDetailImageMemoryCache memoryCache;

	@Override
	public void beforeEach() {
		super.beforeEach();
		memoryCache = new ArtistDetailImageMemoryCache();
	}

	@Test
	public void put_storeValueInCache() {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		memoryCache.put("key1", art);
		assertThat(memoryCache.get("key1")).isSameAs(art);
	}

	@Test
	public void exists_valueExists_returnTrue() {
		Bitmap art = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
		memoryCache.put("key2", art);
		assertThat(memoryCache.exists("key2")).isTrue();
	}

	@Test
	public void exists_valueNotExists_returnFalse() {
		assertThat(memoryCache.exists("key3")).isFalse();
	}
}
