package com.edavtyan.materialplayer.ui.lists.artist_list;

import android.graphics.Bitmap;
import android.util.LruCache;

public class ArtistListImageMemoryCache {
	private static final LruCache<String, Bitmap> cache;

	static {
		cache = new LruCache<>(5); // 4MB
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
