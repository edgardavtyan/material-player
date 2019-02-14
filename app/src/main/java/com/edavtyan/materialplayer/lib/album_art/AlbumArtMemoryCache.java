package com.edavtyan.materialplayer.lib.album_art;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class AlbumArtMemoryCache {
	public static final LruCache<String, Bitmap> cache = new LruCache<>(5);

	public void put(String key, Bitmap image) {
		cache.put(key, image);
	}

	public Bitmap get(String key) {
		return cache.get(key);
	}

	public boolean contains(String key) {
		return cache.get(key) != null;
	}
}
