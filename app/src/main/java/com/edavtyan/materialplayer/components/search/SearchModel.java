package com.edavtyan.materialplayer.components.search;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;

import java.util.List;

public class SearchModel {
	private final ArtistDB artistDB;

	public SearchModel(ArtistDB artistDB) {
		this.artistDB = artistDB;
	}

	public List<Artist> searchArtists(String artistTitle) {
		return artistDB.searchArtists(artistTitle);
	}
}
