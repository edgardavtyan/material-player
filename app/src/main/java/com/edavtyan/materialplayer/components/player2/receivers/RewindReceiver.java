package com.edavtyan.materialplayer.components.player2.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.edavtyan.materialplayer.components.player2.PlayerMvp;

public class RewindReceiver extends BroadcastReceiver {
	private final PlayerMvp.Player player;

	public RewindReceiver(PlayerMvp.Player player) {
		this.player = player;
	}

	@Override public void onReceive(Context context, Intent intent) {
		player.rewind();
	}
}
