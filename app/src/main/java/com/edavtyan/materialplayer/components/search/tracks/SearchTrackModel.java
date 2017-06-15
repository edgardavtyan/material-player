package com.edavtyan.materialplayer.components.search.tracks;

import com.edavtyan.materialplayer.components.search.base.SearchModel;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.db.TrackDB;

import java.util.List;

public class SearchTrackModel extends SearchModel<Track> {
	private final TrackDB trackDB;

	public SearchTrackModel(TrackDB trackDB) {
		this.trackDB = trackDB;
	}

	protected List<Track> getSearchResults(String trackTitle) {
		return trackDB.searchTracks(trackTitle);
	}
}
