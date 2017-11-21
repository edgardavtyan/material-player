package com.edavtyan.materialplayer.components.notification;

import android.support.v4.app.NotificationCompat;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.main.MainActivity;
import com.edavtyan.materialplayer.lib.AdvancedRemoteViews;
import com.edavtyan.materialplayer.lib.testable.TestableNotificationManager;
import com.edavtyan.materialplayer.utils.PendingIntents;

public class PlayerNotificationCompat extends PlayerNotification {

	public PlayerNotificationCompat(
			AdvancedRemoteViews normalRemoteViews,
			AdvancedRemoteViews bigRemoteViews,
			TestableNotificationManager manager,
			NotificationCompat.Builder builder,
			PendingIntents pendingIntents) {
		super(normalRemoteViews, bigRemoteViews, manager);
		setNotification(builder
				.setSmallIcon(R.drawable.ic_status)
				.setContentIntent(pendingIntents.getActivity(MainActivity.class))
				.setContent(normalRemoteViews)
				.setCustomBigContentView(bigRemoteViews)
				.setPriority(NotificationCompat.PRIORITY_MAX)
				.build());
	}
}
