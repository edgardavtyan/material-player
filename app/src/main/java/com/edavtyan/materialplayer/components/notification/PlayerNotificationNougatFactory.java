package com.edavtyan.materialplayer.components.notification;

import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.AdvancedRemoteViews;
import com.edavtyan.materialplayer.lib.testable.TestableNotificationManager;
import com.edavtyan.materialplayer.utils.PendingIntents;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayerNotificationNougatFactory {
	@Provides
	@Singleton
	@Nullable
	public PlayerNotificationNougat provideNotification(
			Context context,
			TestableNotificationManager manager,
			Notification.Builder builder,
			PendingIntents pendingIntents) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			return new PlayerNotificationNougat(
					context,
					new AdvancedRemoteViews(context, R.layout.notification_nougat),
					new AdvancedRemoteViews(context, R.layout.notification_nougat_big),
					manager, builder, pendingIntents);
		} else {
			return null;
		}
	}

	@Provides
	@Singleton
	public TestableNotificationManager provideManager(Context context) {
		return new TestableNotificationManager(NotificationManagerCompat.from(context));
	}

	@Provides
	@Singleton
	public Notification.Builder provideBuilder(Context context) {
		return new Notification.Builder(context);
	}
}
