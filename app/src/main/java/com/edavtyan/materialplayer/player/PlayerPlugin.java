package com.edavtyan.materialplayer.player;

import com.edavtyan.materialplayer.db.types.Track;

public interface PlayerPlugin {
	void onPlayerPluginConnected(Player player);
	void onNewTrack(Track track);
}
