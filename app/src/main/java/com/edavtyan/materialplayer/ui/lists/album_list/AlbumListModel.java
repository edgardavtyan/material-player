package com.edavtyan.materialplayer.ui.lists.album_list;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.lists.lib.ListModel;

import java.util.List;

public class AlbumListModel extends ListModel {

	private final AlbumDB albumDB;
	private final TrackDB trackDB;
	private final AlbumArtProvider artProvider;

	protected List<Album> albums;

	private AlbumListImageTaskQueue queue;

	public AlbumListModel(
			ModelServiceModule serviceModule,
			AlbumDB albumDB,
			TrackDB trackDB,
			AlbumArtProvider artProvider) {
		super(serviceModule);
		this.albumDB = albumDB;
		this.trackDB = trackDB;
		this.artProvider = artProvider;
		queue = new AlbumListImageTaskQueue();
	}

	public Album getAlbumAtIndex(int index) {
		if (albums == null) {
			throw new IllegalStateException("Albums have not been initialized");
		}

		return albums.get(index);
	}

	public List<Track> getAlbumTracks(int position) {
		if (albums == null) {
			throw new IllegalStateException("Albums have not been initialized");
		}

		return trackDB.getTracksWithAlbumId(albums.get(position).getId());
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

	public void loadArts(AlbumArtCallback callback) {
		for (int i = 0; i < getAlbumsCount(); i++) {
			if (artProvider.isCached(getAlbumAtIndex(i).getTitle())) {
				callback.onLoaded(i);
				continue;
			}

			Track track = getAlbumTracks(i).get(0);
			queue.addTask(new AlbumListImageTask(artProvider, callback, queue, track, i));
		}

		if (!queue.isEmpty()) {
			queue.run();
		}
	}

	public String getArtFilename(int position) {
		return artProvider.getFilename(getAlbumAtIndex(position).getTitle());
	}
}
