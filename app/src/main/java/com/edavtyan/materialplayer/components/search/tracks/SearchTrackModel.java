package com.edavtyan.materialplayer.components.search.tracks;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.db.TrackDB;

import java.util.List;

public class SearchTrackModel {
	private final TrackDB trackDB;

	public SearchTrackModel(TrackDB trackDB) {
		this.trackDB = trackDB;
	}

	public List<Track> searchTracks(String trackTitle) {
		return trackDB.searchTracks(trackTitle);
	}
}
