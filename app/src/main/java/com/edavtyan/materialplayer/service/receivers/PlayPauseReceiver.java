package com.edavtyan.materialplayer.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.edavtyan.materialplayer.player.Player;

public class PlayPauseReceiver extends BroadcastReceiver {
	private final Player player;

	public PlayPauseReceiver(Player player) {
		this.player = player;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		player.playPause();
	}
}
