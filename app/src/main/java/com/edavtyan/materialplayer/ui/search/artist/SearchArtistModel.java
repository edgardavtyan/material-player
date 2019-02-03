package com.edavtyan.materialplayer.ui.search.artist;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.lists.artist_list.ArtistListModel;
import com.edavtyan.materialplayer.ui.search.base.SearchModel;

import java.util.List;

public class SearchArtistModel extends ArtistListModel implements SearchModel {
	private final ArtistDB artistDB;

	private String query;

	public SearchArtistModel(
			ModelServiceModule serviceModule,
			ArtistDB artistDB,
			TrackDB trackDB,
			LastfmApi lastfmApi) {
		super(serviceModule, artistDB, trackDB, lastfmApi);
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
