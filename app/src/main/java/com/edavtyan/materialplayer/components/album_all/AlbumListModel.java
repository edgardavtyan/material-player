package com.edavtyan.materialplayer.components.album_all;

import android.content.Context;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.mvp.list.CompactListPref;
import com.edavtyan.materialplayer.lib.mvp.list.ListModel;

import java.util.List;

public class AlbumListModel
		extends ListModel
		implements AlbumListMvp.Model {

	private final AlbumDB albumDB;
	private final TrackDB trackDB;
	private List<Album> albums;

	public AlbumListModel(
			Context context,
			AlbumDB albumDB,
			TrackDB trackDB,
			CompactListPref compactListPref) {
		super(context, compactListPref);
		this.albumDB = albumDB;
		this.trackDB = trackDB;
	}

	@Override
	public Album getAlbumAtIndex(int index) {
		if (albums == null) return null;
		return albums.get(index);
	}

	@Override
	public int getAlbumsCount() {
		if (albums == null) return 0;
		return albums.size();
	}

	@Override
	public void addToPlaylist(int albumId) throws IllegalStateException {
		if (service == null) {
			throw new IllegalStateException(
					"'bindService' should be called before calling 'onAddToPlaylist'");
		}

		service.getPlayer().addManyTracks(trackDB.getTracksWithAlbumId(albumId));
	}

	@Override
	public void update() {
		albums = queryAlbums();
	}

	protected List<Album> queryAlbums() {
		return albumDB.getAllAlbums();
	}
}
