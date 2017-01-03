package com.edavtyan.materialplayer.components.track_all;

import android.content.Context;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.mvp.list.CompactListPref;
import com.edavtyan.materialplayer.lib.mvp.list.ListModel;

import java.util.List;

public class TrackListModel
		extends ListModel
		implements TrackListMvp.Model {

	private final TrackDB db;
	private List<Track> tracks;

	public TrackListModel(Context context, TrackDB db, CompactListPref compactListPref) {
		super(context, compactListPref);
		this.db = db;
	}

	@Override
	public Track getTrackAtIndex(int position) {
		if (tracks == null) return null;
		return tracks.get(position);
	}

	@Override
	public int getItemCount() {
		if (tracks == null) return 0;
		return tracks.size();
	}

	@Override
	public void playQueue(int position) {
		service.getPlayer().playNewTracks(tracks, position);
	}

	@Override
	public void addToQueue(int position) {
		service.getPlayer().addTrack(tracks.get(position));
	}

	@Override
	public void update() {
		tracks = queryTracks();
	}

	@Override
	public void close() {
		tracks = null;
	}

	protected List<Track> queryTracks() {
		return db.getAllTracks();
	}
}
