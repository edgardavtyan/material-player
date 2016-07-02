package com.edavtyan.materialplayer.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public final class PendingIntents {
	private PendingIntents() {}

	public static PendingIntent getBroadcast(Context context, String action) {
		Intent intent = new Intent(action);
		return PendingIntent.getBroadcast(context, 0, intent, 0);
	}

	public static PendingIntent fromIntent(Context context, Intent intent) {
		return PendingIntent.getActivity(context, 0, intent, 0);
	}
}
