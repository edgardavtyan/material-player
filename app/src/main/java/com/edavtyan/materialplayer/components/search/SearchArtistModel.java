package com.edavtyan.materialplayer.components.search;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;

import java.util.List;

public class SearchArtistModel {
	private final ArtistDB artistDB;

	public SearchArtistModel(ArtistDB artistDB) {
		this.artistDB = artistDB;
	}

	public List<Artist> searchArtists(String artistTitle) {
		return artistDB.searchArtists(artistTitle);
	}
}
