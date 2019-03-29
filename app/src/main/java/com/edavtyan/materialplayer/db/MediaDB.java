package com.edavtyan.materialplayer.db;

import com.edavtyan.materialplayer.db.db.AlbumDB;
import com.edavtyan.materialplayer.db.db.ArtistDB;
import com.edavtyan.materialplayer.db.db.TrackDB;

import lombok.experimental.Delegate;

public class MediaDB {
	@Delegate private final ArtistDB artistDB;
	@Delegate private final AlbumDB albumDB;
	@Delegate private final TrackDB trackDB;

	public MediaDB(ArtistDB artistDB, AlbumDB albumDB, TrackDB trackDB) {
		this.artistDB = artistDB;
		this.albumDB = albumDB;
		this.trackDB = trackDB;
	}
}
