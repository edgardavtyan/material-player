package com.edavtyan.materialplayer.components.now_playing_queue;

import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.components.lists.lib.ListModel;
import com.edavtyan.materialplayer.components.player.Player;
import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import lombok.Setter;

public class NowPlayingQueueModel
		extends ListModel
		implements NowPlayingQueueMvp.Model {

	private @Setter OnNewTrackListener onNewTrackListener;

	private final Player.OnNewTrackListener playerOnNewTrackListener
			= new Player.OnNewTrackListener() {
		@Override
		public void onNewTrack() {
			if (onNewTrackListener != null) onNewTrackListener.onNewTrack();
		}
	};

	public NowPlayingQueueModel(ModelServiceModule serviceModule, CompactListPref compactListPref) {
		super(serviceModule, compactListPref);
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
	public void onServiceConnected(PlayerService service) {
		super.onServiceConnected(service);
		service.getPlayer().setOnNewTrackListener(playerOnNewTrackListener);
	}
}
