package com.edavtyan.materialplayer.screens.now_playing_queue;

import com.edavtyan.materialplayer.screens.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.screens.lists.lib.ListModel;
import com.edavtyan.materialplayer.player.Player;
import com.edavtyan.materialplayer.service.PlayerService;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import lombok.Setter;

public class NowPlayingQueueModel extends ListModel {

	private @Setter OnNewTrackListener onNewTrackListener;

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

	public NowPlayingQueueModel(ModelServiceModule serviceModule, CompactListPref compactListPref) {
		super(serviceModule, compactListPref);
	}

	public void playItemAtPosition(int position) {
		service.getPlayer().playTrackAt(position);
	}

	public void removeItemAtPosition(int position) {
		service.getPlayer().removeTrackAt(position);
	}

	public Track getTrackAtPosition(int position) {
		return service.getPlayer().getTrackAt(position);
	}

	public Track getNowPlayingTrack() {
		return service.getPlayer().getCurrentTrack();
	}

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
	public void onServiceConnected(PlayerService service) {
		super.onServiceConnected(service);
		service.getPlayer().setOnNewTrackListener(playerOnNewTrackListener);
	}
}
