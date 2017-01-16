package com.edavtyan.materialplayer.components.player.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.edavtyan.materialplayer.components.player.PlayerMvp;

public class SkipToPreviousReceiver extends BroadcastReceiver {
	private final PlayerMvp.Player player;

	public SkipToPreviousReceiver(PlayerMvp.Player player) {
		this.player = player;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		player.skipToPrevious();
	}
}
