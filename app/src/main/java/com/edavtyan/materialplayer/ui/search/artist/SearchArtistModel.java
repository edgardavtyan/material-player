package com.edavtyan.materialplayer.ui.search.artist;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.MediaDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.lists.artist_list.ArtistListImageLoader;
import com.edavtyan.materialplayer.ui.lists.artist_list.ArtistListModel;
import com.edavtyan.materialplayer.ui.search.base.SearchModel;

import java.util.List;

public class SearchArtistModel extends ArtistListModel implements SearchModel {
	private final MediaDB mediaDB;

	private String query;

	public SearchArtistModel(
			ModelServiceModule serviceModule,
			MediaDB mediaDB,
			ArtistListImageLoader imageLoader) {
		super(serviceModule, mediaDB, imageLoader);
		this.mediaDB = mediaDB;
	}

	@Override
	protected List<Artist> queryArtists() {
		return mediaDB.searchArtists(query);
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
