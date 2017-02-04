package com.edavtyan.materialplayer.components.player.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.edavtyan.materialplayer.components.player.PlayOnHeadsetPluggedPref;
import com.edavtyan.materialplayer.components.player.PlayerMvp;

public class HeadphonesConnectedReceiver extends BroadcastReceiver {
	private static final String EXTRA_STATE = "state";
	private static final int STATE_PLUGGED = 1;

	private final PlayerMvp.Player player;
	private final PlayOnHeadsetPluggedPref playOnHeadsetPluggedPref;

	public HeadphonesConnectedReceiver(
			PlayerMvp.Player player,
			PlayOnHeadsetPluggedPref playOnHeadsetPluggedPref) {
		this.player = player;
		this.playOnHeadsetPluggedPref = playOnHeadsetPluggedPref;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		int state = intent.getIntExtra(EXTRA_STATE, -1);

		if (playOnHeadsetPluggedPref.isEnabled() && state == STATE_PLUGGED) {
			player.play();
		}
	}
}
