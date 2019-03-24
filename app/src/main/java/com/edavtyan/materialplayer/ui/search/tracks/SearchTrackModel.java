package com.edavtyan.materialplayer.ui.search.tracks;

import com.edavtyan.materialplayer.db.MediaDB;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.lists.track_list.TrackListModel;
import com.edavtyan.materialplayer.ui.search.base.SearchModel;

import java.util.List;

import lombok.Setter;

public class SearchTrackModel extends TrackListModel implements SearchModel {
	private final MediaDB mediaDB;

	private @Setter String query;

	public SearchTrackModel(ModelServiceModule serviceModule, MediaDB mediaDB) {
		super(serviceModule, mediaDB);
		this.mediaDB = mediaDB;
	}

	@Override
	protected List<Track> queryTracks() {
		return mediaDB.searchTracks(query);
	}

	@Override
	public int getSearchResultCount() {
		return getItemCount();
	}
}
