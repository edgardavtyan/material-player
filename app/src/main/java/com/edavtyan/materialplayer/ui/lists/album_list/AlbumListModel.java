package com.edavtyan.materialplayer.ui.lists.album_list;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.lists.lib.ListModel;

import java.util.List;

public class AlbumListModel extends ListModel {

	private final AlbumDB albumDB;
	private final TrackDB trackDB;

	private List<Album> albums;

	public AlbumListModel(
			ModelServiceModule serviceModule,
			AlbumDB albumDB,
			TrackDB trackDB) {
		super(serviceModule);
		this.albumDB = albumDB;
		this.trackDB = trackDB;
	}

	public Album getAlbumAtIndex(int index) {
		if (albums == null) {
			throw new IllegalStateException("Albums have not been initialized");
		}

		return albums.get(index);
	}

	public int getAlbumsCount() {
		if (albums == null) return 0;
		return albums.size();
	}

	public void addToPlaylist(int albumId) {
		getService().getPlayer().addManyTracks(trackDB.getTracksWithAlbumId(albumId));
	}

	public void update() {
		albums = queryAlbums();
	}

	protected List<Album> queryAlbums() {
		return albumDB.getAllAlbums();
	}
}
