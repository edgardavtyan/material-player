package com.edavtyan.materialplayer.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.edavtyan.materialplayer.player.Player;

public class AudioBecomingNoisyReceiver extends BroadcastReceiver {
	private final Player player;

	public AudioBecomingNoisyReceiver(Player player) {
		this.player = player;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		player.pause();
	}
}
