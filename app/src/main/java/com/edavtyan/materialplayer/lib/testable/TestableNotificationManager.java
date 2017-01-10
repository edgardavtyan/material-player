package com.edavtyan.materialplayer.lib.testable;

import android.app.Notification;
import android.support.v4.app.NotificationManagerCompat;

public class TestableNotificationManager {
	private final NotificationManagerCompat manager;

	public TestableNotificationManager(NotificationManagerCompat manager) {
		this.manager = manager;
	}

	public void notify(int id, Notification notification) {
		manager.notify(id, notification);
	}
}
