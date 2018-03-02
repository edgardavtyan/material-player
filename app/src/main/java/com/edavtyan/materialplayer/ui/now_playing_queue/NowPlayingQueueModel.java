package com.edavtyan.materialplayer.ui.now_playing_queue;

import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.service.PlayerService;
import com.edavtyan.materialplayer.ui.lists.lib.ListModel;
import com.edavtyan.materialplayer.ui.lists.lib.ServiceNotConnectedException;

import lombok.Setter;

public class NowPlayingQueueModel extends ListModel {

	private @Setter @Nullable OnNewTrackListener onNewTrackListener;

	private final Player.OnNewTrackListener playerOnNewTrackListener
			= new Player.OnNewTrackListener() {
		@Override
		public void onNewTrack() {
			if (onNewTrackListener != null) onNewTrackListener.onNewTrack();
		}
	};

	interface OnNewTrackListener {
		void onNewTrack();
	}

	public NowPlayingQueueModel(ModelServiceModule serviceModule) {
		super(serviceModule);
	}

	public void playItemAtPosition(int position) {
		getService().getPlayer().playTrackAt(position);
	}

	public void removeItemAtPosition(int position) {
		getService().getPlayer().removeTrackAt(position);
	}

	public Track getTrackAtPosition(int position) {
		return getService().getPlayer().getTrackAt(position);
	}

	public Track getNowPlayingTrack() {
		return getService().getPlayer().getCurrentTrack();
	}

	public int getTrackCount() {
		try {
			return getService().getPlayer().getTracksCount();
		} catch (ServiceNotConnectedException e) {
			return 0;
		}
	}

	@Override
	public void unbindService() {
		getService().getPlayer().removeOnNewTrackListener(playerOnNewTrackListener);
		super.unbindService();
	}

	@Override
	public void onServiceConnected(PlayerService service) {
		super.onServiceConnected(service);
		service.getPlayer().setOnNewTrackListener(playerOnNewTrackListener);
	}
}
