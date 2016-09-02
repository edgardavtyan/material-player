package com.edavtyan.materialplayer.components.track_mvp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.edavtyan.materialplayer.MusicPlayerService;
import com.edavtyan.materialplayer.lib.db.TrackDB;
import com.edavtyan.materialplayer.components.tracks.Track;

import java.util.List;

import lombok.Setter;

public class TrackListModel implements TrackListMvp.Model, ServiceConnection {
	private final Context context;
	private final TrackDB db;
	private List<Track> tracks;
	private MusicPlayerService service;

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
		service.playQueue(tracks, position);
	}

	@Override
	public void addToQueue(int position) {
		service.addToQueue(tracks.get(position));
	}

	@Override
	public void bindService() {
		context.bindService(
				new Intent(context, MusicPlayerService.class),
				this, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void unbindService() {
		context.unbindService(this);
	}

	@Override
	public void update() {
		tracks = db.getAllTracks();
	}

	@Override
	public void close() {
		tracks = null;
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((MusicPlayerService.MusicPlayerBinder) binder).getService();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {

	}
}
