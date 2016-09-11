package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.edavtyan.materialplayer.MusicPlayerService;
import com.edavtyan.materialplayer.db.Track;

public class PlaylistModel implements PlaylistMvp.Model {
	private final Context context;
	private MusicPlayerService service;

	public PlaylistModel(Context context) {
		this.context = context;
	}

	@Override
	public void bind() {
		Intent intent = new Intent(context, MusicPlayerService.class);
		context.bindService(intent, this, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void unbind() {
		context.unbindService(this);
	}

	@Override
	public void playItemAtPosition(int position) {
		service.switchQueueTrackToPosition(position);
	}

	@Override
	public void removeItemAtPosition(int position) {
		service.removeQueueTrackAtPosition(position);
	}

	@Override
	public Track getTrackAtPosition(int position) {
		return service.getQueueTrackAt(position);
	}

	@Override
	public int getTrackCount() {
		if (service == null) return 0;
		return service.getQueueCount();
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((MusicPlayerService.MusicPlayerBinder)binder).getService();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
	}
}
