package com.edavtyan.materialplayer.components.artist_all;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.lib.mvp.list.ListModel;
import com.edavtyan.materialplayer.lib.prefs.AdvancedSharedPrefs;

import java.util.List;

public class ArtistListModel
		extends ListModel
		implements ArtistListMvp.Model {

	private final ArtistDB db;
	private List<Artist> artists;

	public ArtistListModel(ArtistDB db, AdvancedSharedPrefs prefs) {
		super(prefs);
		this.db = db;
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
}
