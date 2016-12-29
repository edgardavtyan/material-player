package com.edavtyan.materialplayer.components.album_detail;

import android.content.Context;
import android.graphics.Bitmap;

import com.edavtyan.materialplayer.components.track_all.TrackListModel;
import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.utils.ArtProvider2;

import java.util.List;

public class AlbumDetailModel extends TrackListModel implements AlbumDetailMvp.Model {
	private final AlbumDB albumDB;
	private final TrackDB trackDB;
	private final ArtProvider2 artProvider;
	private final int albumId;

	public AlbumDetailModel(
			Context context,
			AlbumDB albumDB,
			TrackDB trackDB,
			ArtProvider2 artProvider,
			int albumId) {
		super(context, trackDB);
		this.albumDB = albumDB;
		this.trackDB = trackDB;
		this.artProvider = artProvider;
		this.albumId = albumId;
	}

	@Override
	public Album getAlbum() {
		return albumDB.getAlbumWithAlbumId(albumId);
	}

	@Override
	public Bitmap getAlbumArt() {
		return artProvider.load(getTrackAtIndex(0));
	}

	@Override
	protected List<Track> queryTracks() {
		return trackDB.getTracksWithAlbumId(albumId);
	}
}
