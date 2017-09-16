package com.edavtyan.materialplayer.components.search.tracks;

import android.content.Context;

import com.edavtyan.materialplayer.components.search.base.SearchModel;
import com.edavtyan.materialplayer.components.lists.track_list.TrackListModel;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;

import java.util.List;

import lombok.Setter;

public class SearchTrackModel extends TrackListModel implements SearchModel {
	private final TrackDB trackDB;

	private @Setter String query;

	public SearchTrackModel(Context context, TrackDB trackDB, CompactListPref compactListPref) {
		super(context, trackDB, compactListPref);
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
