package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.db.Track;

import lombok.Setter;

public class NowPlayingQueueModel implements NowPlayingQueueMvp.Model {
	private final Context context;
	private PlayerService service;

	private @Setter OnServiceConnectedListener onServiceConnectedListener;

	public NowPlayingQueueModel(Context context) {
		this.context = context;
	}

	@Override
	public void bind() {
		Intent intent = new Intent(context, PlayerService.class);
		context.bindService(intent, this, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void unbind() {
		context.unbindService(this);
	}

	@Override
	public void playItemAtPosition(int position) {
		service.getPlayer().playTrackAt(position);
	}

	@Override
	public void removeItemAtPosition(int position) {
		service.getPlayer().removeTrackAt(position);
	}

	@Override
	public Track getTrackAtPosition(int position) {
		return service.getPlayer().getTrackAt(position);
	}

	@Override
	public int getTrackCount() {
		if (service == null) return 0;
		return service.getPlayer().getTracksCount();
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		service = ((PlayerService.PlayerBinder)binder).getService();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
	}
}
