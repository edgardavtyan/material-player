package com.edavtyan.materialplayer.components.now_playing_queue;

import android.content.Context;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.mvp.list.CompactListPref;
import com.edavtyan.materialplayer.lib.mvp.list.ListModel;

public class NowPlayingQueueModel extends ListModel implements NowPlayingQueueMvp.Model {
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
	public int getTrackCount() {
		if (service == null) return 0;
		return service.getPlayer().getTracksCount();
	}
}
