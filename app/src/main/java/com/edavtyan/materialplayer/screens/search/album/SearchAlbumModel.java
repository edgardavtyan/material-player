package com.edavtyan.materialplayer.screens.search.album;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.screens.lists.album_list.AlbumListModel;
import com.edavtyan.materialplayer.screens.search.base.SearchModel;

import java.util.List;

import lombok.Setter;

public class SearchAlbumModel extends AlbumListModel implements SearchModel {
	private final AlbumDB albumDB;

	private @Setter String query;

	public SearchAlbumModel(
			ModelServiceModule serviceModule,
			AlbumDB albumDB,
			TrackDB trackDB) {
		super(serviceModule, albumDB, trackDB);
		this.albumDB = albumDB;
	}

	@Override
	protected List<Album> queryAlbums() {
		return albumDB.searchAlbums(query);
	}

	@Override
	public int getSearchResultCount() {
		return getAlbumsCount();
	}
}
