package com.edavtyan.materialplayer.screens.search.artist;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.screens.lists.artist_list.ArtistListImageLoader;
import com.edavtyan.materialplayer.screens.lists.artist_list.ArtistListModel;
import com.edavtyan.materialplayer.screens.search.base.SearchModel;

import java.util.List;

public class SearchArtistModel extends ArtistListModel implements SearchModel {
	private final ArtistDB artistDB;

	private String query;

	public SearchArtistModel(
			ModelServiceModule serviceModule,
			ArtistDB artistDB,
			TrackDB trackDB,
			ArtistListImageLoader imageLoader) {
		super(serviceModule, artistDB, trackDB, imageLoader);
		this.artistDB = artistDB;
	}

	@Override
	protected List<Artist> queryArtists() {
		return artistDB.searchArtists(query);
	}

	@Override
	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public int getSearchResultCount() {
		return getArtistCount();
	}
}
