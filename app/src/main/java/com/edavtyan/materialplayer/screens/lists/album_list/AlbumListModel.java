package com.edavtyan.materialplayer.screens.lists.album_list;

import com.edavtyan.materialplayer.screens.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.screens.lists.lib.ListModel;
import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import java.util.List;

public class AlbumListModel extends ListModel {

	private final AlbumDB albumDB;
	private final TrackDB trackDB;

	private List<Album> albums;

	public AlbumListModel(
			ModelServiceModule serviceModule,
			AlbumDB albumDB,
			TrackDB trackDB,
			CompactListPref compactListPref) {
		super(serviceModule, compactListPref);
		this.albumDB = albumDB;
		this.trackDB = trackDB;
	}

	public Album getAlbumAtIndex(int index) {
		if (albums == null) return null;
		return albums.get(index);
	}

	public int getAlbumsCount() {
		if (albums == null) return 0;
		return albums.size();
	}

	public void addToPlaylist(int albumId) {
		service.getPlayer().addManyTracks(trackDB.getTracksWithAlbumId(albumId));
	}

	public void update() {
		albums = queryAlbums();
	}

	protected List<Album> queryAlbums() {
		return albumDB.getAllAlbums();
	}
}
