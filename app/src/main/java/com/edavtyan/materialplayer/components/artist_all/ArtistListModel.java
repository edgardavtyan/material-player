package com.edavtyan.materialplayer.components.artist_all;

import android.content.SharedPreferences;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import java.util.List;

import lombok.Setter;

public class ArtistListModel
		implements ArtistListMvp.Model,
				   SharedPreferences.OnSharedPreferenceChangeListener {

	private static final String PREF_COMPACT_LISTS_KEY = "pref_compactList";
	private static final boolean PREF_COMPACT_LISTS_DEFAULT = false;

	private final ArtistDB db;
	private final AdvancedSharedPrefs prefs;
	private List<Artist> artists;

	private @Setter OnCompactListsPrefChangedListener onCompactListsPrefChangedListener;

	public ArtistListModel(ArtistDB db, AdvancedSharedPrefs prefs) {
		this.db = db;
		this.prefs = prefs;
		this.prefs.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void update() {
		artists = db.getAllArtists();
	}

	@Override
	public int getArtistCount() {
		if (artists == null) return 0;
		return artists.size();
	}

	@Override
	public Artist getArtistAtIndex(int position) {
		if (artists == null) return null;
		return artists.get(position);
	}

	@Override
	public boolean isCompactListsEnabled() {
		return prefs.getBoolean(PREF_COMPACT_LISTS_KEY, PREF_COMPACT_LISTS_DEFAULT);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(PREF_COMPACT_LISTS_KEY) && onCompactListsPrefChangedListener != null) {
			onCompactListsPrefChangedListener.onCompactListsPrefChanged(isCompactListsEnabled());
		}
	}
}
