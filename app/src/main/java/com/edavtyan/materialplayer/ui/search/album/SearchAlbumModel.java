package com.edavtyan.materialplayer.ui.search.album;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.lists.album_list.AlbumListModel;
import com.edavtyan.materialplayer.ui.search.base.SearchModel;

import java.util.List;

import lombok.Setter;

public class SearchAlbumModel extends AlbumListModel implements SearchModel {
	private final AlbumDB albumDB;

	private @Setter String query;

	public SearchAlbumModel(
			ModelServiceModule serviceModule,
			AlbumDB albumDB,
			TrackDB trackDB,
			AlbumArtProvider albumArtProvider) {
		super(serviceModule, albumDB, trackDB, albumArtProvider);
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
