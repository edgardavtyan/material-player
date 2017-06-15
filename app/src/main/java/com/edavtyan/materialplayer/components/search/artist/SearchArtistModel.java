package com.edavtyan.materialplayer.components.search.artist;

import com.edavtyan.materialplayer.components.search.base.SearchModel;
import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;

import java.util.List;

public class SearchArtistModel extends SearchModel<Artist> {
	private final ArtistDB artistDB;

	public SearchArtistModel(ArtistDB artistDB) {
		this.artistDB = artistDB;
	}

	public List<Artist> getSearchResults(String query) {
		return artistDB.searchArtists(query);
	}
}
