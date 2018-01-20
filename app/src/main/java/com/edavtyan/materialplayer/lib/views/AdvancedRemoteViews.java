package com.edavtyan.materialplayer.lib.views;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class AdvancedRemoteViews extends RemoteViews {

	private final Context context;

	public AdvancedRemoteViews(Context context, int layoutId) {
		super(context.getPackageName(), layoutId);
		this.context = context;
	}

	public void setOnClickBroadcast(int viewId, String action) {
		Intent intent = new Intent(action);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
		super.setOnClickPendingIntent(viewId, pendingIntent);
	}
}
