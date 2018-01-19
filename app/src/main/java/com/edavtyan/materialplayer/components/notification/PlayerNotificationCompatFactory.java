package com.edavtyan.materialplayer.components.notification;

import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.AdvancedRemoteViews;
import com.edavtyan.materialplayer.lib.testable.TestableNotificationManager;
import com.edavtyan.materialplayer.utils.PendingIntents;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayerNotificationCompatFactory {
	@Provides
	@Singleton
	public PlayerNotificationCompat provideNotification(
			Context context,
			TestableNotificationManager manager,
			NotificationCompat.Builder builder,
			PendingIntents pendingIntents) {
		return new PlayerNotificationCompat(
				new AdvancedRemoteViews(context, R.layout.notification),
				new AdvancedRemoteViews(context, R.layout.notification_big),
				manager, builder, pendingIntents);
	}

	@Provides
	@Singleton
	public NotificationCompat.Builder provideBuilder(Context context) {
		return new NotificationCompat.Builder(context);
	}
}
