package com.edavtyan.materialplayer.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import com.edavtyan.materialplayer.player.Player;

public class MediaButtonReceiver extends BroadcastReceiver {
	private final Player player;

	public MediaButtonReceiver(Player player) {
		this.player = player;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		int keyEvent = intent.getIntExtra(Intent.EXTRA_KEY_EVENT, -1);
		switch (keyEvent) {
		case KeyEvent.KEYCODE_MEDIA_PLAY:
			player.play();
			break;
		case KeyEvent.KEYCODE_MEDIA_PAUSE:
			player.pause();
			break;
		case KeyEvent.KEYCODE_MEDIA_NEXT:
			player.skipToNext();
			break;
		case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
			player.skipToPrevious();
		}
	}
}
