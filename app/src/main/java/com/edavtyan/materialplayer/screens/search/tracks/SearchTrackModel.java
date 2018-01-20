package com.edavtyan.materialplayer.screens.search.tracks;

import com.edavtyan.materialplayer.screens.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.screens.lists.track_list.TrackListModel;
import com.edavtyan.materialplayer.screens.search.base.SearchModel;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import java.util.List;

import lombok.Setter;

public class SearchTrackModel extends TrackListModel implements SearchModel {
	private final TrackDB trackDB;

	private @Setter String query;

	public SearchTrackModel(ModelServiceModule serviceModule, TrackDB trackDB, CompactListPref compactListPref) {
		super(serviceModule, trackDB, compactListPref);
		this.trackDB = trackDB;
	}

	@Override
	protected List<Track> queryTracks() {
		return trackDB.searchTracks(query);
	}

	@Override
	public int getSearchResultCount() {
		return getItemCount();
	}
}
