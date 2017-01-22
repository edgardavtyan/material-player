package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;

import com.edavtyan.materialplayer.components.player.PlayerMvp;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.mvp.list.CompactListPref;
import com.edavtyan.materialplayer.lib.mvp.list.ListModel;

import lombok.Setter;

public class NowPlayingQueueModel
		extends ListModel
		implements NowPlayingQueueMvp.Model {

	private @Setter OnNewTrackListener onNewTrackListener;

	private PlayerMvp.Player.OnNewTrackListener playerOnNewTrackListener = () -> {
		if (onNewTrackListener != null) onNewTrackListener.onNewTrack();
	};

	public NowPlayingQueueModel(Context context, CompactListPref compactListPref) {
		super(context, compactListPref);
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
	public Track getNowPlayingTrack() {
		return service.getPlayer().getCurrentTrack();
	}

	@Override
	public int getTrackCount() {
		if (service == null) return 0;
		return service.getPlayer().getTracksCount();
	}

	@Override
	public void unbindService() {
		service.getPlayer().removeOnNewTrackListener(playerOnNewTrackListener);
		super.unbindService();
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder binder) {
		super.onServiceConnected(name, binder);
		service.getPlayer().setOnNewTrackListener(playerOnNewTrackListener);
	}
}
