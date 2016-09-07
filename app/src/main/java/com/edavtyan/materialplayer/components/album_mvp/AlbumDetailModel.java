package com.edavtyan.materialplayer.components.album_mvp;

import com.edavtyan.materialplayer.components.track_mvp.TrackListModel;
import com.edavtyan.materialplayer.components.tracks.Track;
import com.edavtyan.materialplayer.lib.db.AlbumDB;
import com.edavtyan.materialplayer.lib.db.ArtistDB;
import com.edavtyan.materialplayer.lib.db.TrackDB;

import java.util.List;

public class AlbumDetailModel extends TrackListModel implements AlbumDetailMvp.Model {
	private final AlbumDB albumDB;
	private final TrackDB trackDB;
	private final int albumId;

	public AlbumDetailModel(
			AlbumDetailActivity view,
			ArtistDB artistDB,
			AlbumDB albumDB,
			TrackDB trackDB,
			int albumId) {
		super(view, trackDB);
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
