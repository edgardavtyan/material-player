package com.edavtyan.materialplayer.components.search.album;

import com.edavtyan.materialplayer.components.search.base.SearchModel;
import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;

import java.util.List;

public class SearchAlbumModel extends SearchModel<Album> {
	private final AlbumDB albumDB;

	public SearchAlbumModel(AlbumDB albumDB) {
		this.albumDB = albumDB;
	}

	public List<Album> getSearchResults(String query) {
		return albumDB.searchAlbums(query);
	}
}
