package com.edavtyan.materialplayer.lib.testable;

import android.support.v4.app.NotificationManagerCompat;

import lombok.experimental.Delegate;

public class TestableNotificationManager {
	private final @Delegate NotificationManagerCompat manager;

	public TestableNotificationManager(NotificationManagerCompat manager) {
		this.manager = manager;
	}
}
