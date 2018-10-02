package com.edavtyan.materialplayer.ui.search.tracks;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.lists.playlist_list.PlaylistManager;
import com.edavtyan.materialplayer.ui.lists.track_list.TrackListModel;
import com.edavtyan.materialplayer.ui.search.base.SearchModel;

import java.util.List;

import lombok.Setter;

public class SearchTrackModel extends TrackListModel implements SearchModel {
	private final TrackDB trackDB;

	private @Setter String query;

	public SearchTrackModel(
			ModelServiceModule serviceModule,
			TrackDB trackDB,
			PlaylistManager playlistManager) {
		super(serviceModule, trackDB);
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
