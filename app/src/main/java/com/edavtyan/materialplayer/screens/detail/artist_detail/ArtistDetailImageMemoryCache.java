package com.edavtyan.materialplayer.screens.detail.artist_detail;

import android.graphics.Bitmap;
import android.util.LruCache;

public class ArtistDetailImageMemoryCache {
	private static final LruCache<String, Bitmap> cache;

	static {
		cache = new LruCache<>(1000 * 1000 * 4); // 4MB
	}

	public boolean exists(String key) {
		return cache.get(key) != null;
	}

	public void put(String key, Bitmap image) {
		cache.put(key, image);
	}

	public Bitmap get(String key) {
		return cache.get(key);
	}
}
