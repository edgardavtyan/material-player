package com.edavtyan.materialplayer.components.track_all;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.db.TrackDB;

import java.util.List;

public class TrackListModel implements TrackListMvp.Model, ServiceConnection {
	private final Context context;
	private final TrackDB db;
	private List<Track> tracks;
	private PlayerService service;

	public TrackListModel(Context context, TrackDB db) {
		this.context = context;
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
	public void bindService() {
		context.bindService(
				new Intent(context, PlayerService.class),
				this, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void unbindService() {
		context.unbindService(this);
	}

	@Override
	public void update() {
		tracks = queryTracks();
	}

	@Override
	public void close() {
		tracks = null;
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((PlayerService.PlayerBinder) binder).getService();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
	}

	protected List<Track> queryTracks() {
		return db.getAllTracks();
	}
}
