package com.edavtyan.materialplayer.components.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.main.MainActivity;
import com.edavtyan.materialplayer.lib.AdvancedRemoteViews;
import com.edavtyan.materialplayer.lib.testable.TestableNotificationManager;
import com.edavtyan.materialplayer.utils.PendingIntents;

@TargetApi(24)
public class PlayerNotificationNougat extends PlayerNotification {
	public PlayerNotificationNougat(
			Context context,
			AdvancedRemoteViews normalRemoteViews,
			AdvancedRemoteViews bigRemoteViews,
			TestableNotificationManager manager,
			Notification.Builder builder,
			PendingIntents pendingIntents) {
		super(normalRemoteViews, bigRemoteViews, manager);
		setNotification(builder
				.setSmallIcon(R.drawable.ic_status)
				.setContentIntent(pendingIntents.getActivity(MainActivity.class))
				.setCustomContentView(normalRemoteViews)
				.setCustomBigContentView(bigRemoteViews)
				.setStyle(new Notification.DecoratedCustomViewStyle())
				.setColor(ContextCompat.getColor(context, R.color.primary_orange))
				.build());
	}
}
