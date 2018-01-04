package com.edavtyan.materialplayer.components.lists.track_list;

import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.components.lists.lib.ListModel;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import java.util.List;

public class TrackListModel extends ListModel {

	protected List<Track> tracks;
	private final TrackDB db;

	public TrackListModel(ModelServiceModule serviceModule, TrackDB db, CompactListPref compactListPref) {
		super(serviceModule, compactListPref);
		this.db = db;
	}

	public Track getTrackAtIndex(int position) {
		if (tracks == null) return null;
		return tracks.get(position);
	}

	public int getItemCount() {
		if (tracks == null) return 0;
		return tracks.size();
	}

	public void playQueue(int position) {
		service.getPlayer().playNewTracks(tracks, position);
	}

	public void addToQueue(int position) {
		service.getPlayer().addTrack(tracks.get(position));
	}

	public void update() {
		tracks = queryTracks();
	}

	public void close() {
		tracks = null;
	}

	protected List<Track> queryTracks() {
		return db.getAllTracks();
	}
}
