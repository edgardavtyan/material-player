package com.edavtyan.materialplayer.lib.album_art;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class AlbumArtMemoryCache {
	public static final LruCache<Integer, Bitmap> cache = new LruCache<>(5);

	public void put(int key, Bitmap image) {
		cache.put(key, image);
	}

	public Bitmap get(int key) {
		return cache.get(key);
	}

	public boolean contains(int key) {
		return cache.get(key) != null;
	}
}
