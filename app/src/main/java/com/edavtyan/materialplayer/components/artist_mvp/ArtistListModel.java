package com.edavtyan.materialplayer.components.artist_mvp;

import com.edavtyan.materialplayer.components.artists.Artist;

import java.util.List;

public class ArtistListModel implements ArtistListMvp.Model {
	private final ArtistDB db;
	private List<Artist> artists;

	public ArtistListModel(ArtistDB db) {
		this.db = db;
		updateData();
	}

	public void updateData() {
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
