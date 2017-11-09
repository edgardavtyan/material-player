package com.edavtyan.materialplayer.components.detail.album_detail;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.components.lists.track_list.TrackListModel;
import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import java.util.List;

public class AlbumDetailModel extends TrackListModel implements AlbumDetailMvp.Model {
	private final AlbumDB albumDB;
	private final TrackDB trackDB;
	private final AlbumArtProvider albumArtProvider;
	private final int albumId;

	public AlbumDetailModel(
			ModelServiceModule serviceModule,
			AlbumDB albumDB,
			TrackDB trackDB,
			AlbumArtProvider albumArtProvider,
			CompactListPref prefs,
			int albumId) {
		super(serviceModule, trackDB, prefs);
		this.albumDB = albumDB;
		this.trackDB = trackDB;
		this.albumArtProvider = albumArtProvider;
		this.albumId = albumId;
	}

	@Override
	public Album getAlbum() {
		return albumDB.getAlbumWithAlbumId(albumId);
	}

	@Override
	public Bitmap getAlbumArt() {
		return albumArtProvider.load(getTrackAtIndex(0));
	}

	@Override
	public long getTotalAlbumDuration() {
		long totalDurationMS = 0;
		for (Track track : tracks) {
			totalDurationMS += track.getDuration();
		}

		return totalDurationMS;
	}

	@Override
	protected List<Track> queryTracks() {
		return trackDB.getTracksWithAlbumId(albumId);
	}
}
