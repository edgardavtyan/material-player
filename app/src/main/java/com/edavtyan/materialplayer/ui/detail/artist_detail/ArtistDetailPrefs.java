package com.edavtyan.materialplayer.ui.detail.artist_detail;

import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

public class ArtistDetailPrefs {
	private static final String PREF_KEY = "pref_album_sort";

	private final AdvancedSharedPrefs prefs;

	public ArtistDetailPrefs(AdvancedSharedPrefs prefs) {
		this.prefs = prefs;
	}

	public String getSort() {
		return prefs.getString(PREF_KEY, AlbumDB.KEY_TITLE);
	}

	public void saveSort(String sortKey) {
		prefs.edit().putString(PREF_KEY, sortKey).apply();
	}
}
