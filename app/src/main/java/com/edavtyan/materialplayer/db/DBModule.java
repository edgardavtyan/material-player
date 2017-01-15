package com.edavtyan.materialplayer.db;

import android.content.Context;

public class DBModule {
	private final Context context;

	private ArtistDB artistDB;
	private AlbumDB albumDB;
	private TrackDB trackDB;

	public DBModule(Context context) {
		this.context = context;
	}

	public ArtistDB getArtistDB() {
		if (artistDB == null)
			artistDB = new ArtistDB(context);
		return artistDB;
	}

	public AlbumDB getAlbumDB() {
		if (albumDB == null)
			albumDB = new AlbumDB(context);
		return albumDB;
	}

	public TrackDB getTrackDB() {
		if (trackDB == null)
			trackDB = new TrackDB(context);
		return trackDB;
	}
}
