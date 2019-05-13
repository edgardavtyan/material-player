package com.edavtyan.materialplayer.ui.lists.artist_list;

import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.lib.prefs.AdvancedGsonSharedPrefs;

import java.util.HashMap;

public class ArtistListImageLinkCache {
	private static final String PREF_KEY = "pref_imageLink";

	private final AdvancedGsonSharedPrefs prefs;

	private HashMap<String, String> cacheMap;

	public ArtistListImageLinkCache(AdvancedGsonSharedPrefs prefs) {
		this.prefs = prefs;
		this.cacheMap = prefs.getMap(PREF_KEY);
		if (cacheMap == null) {
			cacheMap = new HashMap<>();
		}
	}

	public void addLink(String artist, String link) {
		prefs.appendToMap(PREF_KEY, artist, link);
		cacheMap = prefs.getMap(PREF_KEY);
	}

	@Nullable
	public String getLink(String artist) {
		return cacheMap.get(artist);
	}

	public boolean exists(String artist) {
		return getLink(artist) != null;
	}
}
