package com.edavtyan.materialplayer.components.album_detail;

import android.content.Context;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.components.track_all.TrackListModel;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;

import java.util.List;

public class AlbumDetailModel extends TrackListModel implements AlbumDetailMvp.Model {
	private final AlbumDB albumDB;
	private final TrackDB trackDB;
	private final int albumId;

	public AlbumDetailModel(
			Context context,
			ArtistDB artistDB,
			AlbumDB albumDB,
			TrackDB trackDB,
			int albumId) {
		super(context, trackDB);
		this.albumDB = albumDB;
		this.trackDB = trackDB;
		this.albumId = albumId;
	}

	@Override
	public Album getAlbum() {
		return albumDB.getAlbumWithAlbumId(albumId);
	}

	@Override
	public Track getFirstAlbumTrack() {
		return trackDB.getFirstTrackWithAlbumId(albumId);
	}

	@Override
	protected List<Track> queryTracks() {
		return trackDB.getTracksWithAlbumId(albumId);
	}
}
