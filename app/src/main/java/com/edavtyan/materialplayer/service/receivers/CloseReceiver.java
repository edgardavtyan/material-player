package com.edavtyan.materialplayer.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CloseReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		System.exit(1);
	}
}
