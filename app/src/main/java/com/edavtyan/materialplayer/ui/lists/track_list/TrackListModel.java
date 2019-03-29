package com.edavtyan.materialplayer.ui.lists.track_list;

import com.edavtyan.materialplayer.db.MediaDB;
import com.edavtyan.materialplayer.db.types.Track;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.lists.lib.ListModel;

import java.util.List;

public class TrackListModel extends ListModel {

	protected List<Track> tracks;

	private final MediaDB mediaDB;

	public TrackListModel(ModelServiceModule serviceModule, MediaDB mediaDB) {
		super(serviceModule);
		this.mediaDB = mediaDB;
	}

	public Track getTrackAtIndex(int position) {
		if (tracks == null) {
			throw new IllegalStateException("Tracks have not been initialized");
		}

		return tracks.get(position);
	}

	public int getItemCount() {
		if (tracks == null) return 0;
		return tracks.size();
	}

	public void playQueue(int position) {
		getService().getPlayer().playNewTracks(tracks, position);
	}

	public void addToQueue(int position) {
		getService().getPlayer().addTrack(tracks.get(position));
	}

	public void update() {
		tracks = queryTracks();
	}

	public void close() {
		tracks = null;
	}

	protected List<Track> queryTracks() {
		return mediaDB.getAllTracks();
	}
}
