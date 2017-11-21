package com.edavtyan.materialplayer.components.player.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.edavtyan.materialplayer.components.player.Player;

public class SkipToNextReceiver extends BroadcastReceiver {
	private final Player player;

	public SkipToNextReceiver(Player player) {
		this.player = player;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		player.skipToNext();
	}
}
