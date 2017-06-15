package com.edavtyan.materialplayer.components.search.album;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;

import java.util.List;

public class SearchAlbumModel {
	private final AlbumDB albumDB;

	public SearchAlbumModel(AlbumDB albumDB) {
		this.albumDB = albumDB;
	}

	public List<Album> searchAlbums(String albumTitle) {
		return albumDB.searchAlbums(albumTitle);
	}
}
