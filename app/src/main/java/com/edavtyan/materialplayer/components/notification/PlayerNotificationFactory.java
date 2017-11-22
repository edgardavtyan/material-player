package com.edavtyan.materialplayer.components.notification;

import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.AdvancedRemoteViews;
import com.edavtyan.materialplayer.lib.base.BaseFactory;
import com.edavtyan.materialplayer.lib.testable.TestableNotificationManager;
import com.edavtyan.materialplayer.utils.PendingIntents;

public class PlayerNotificationFactory extends BaseFactory {
	private PlayerNotificationMvp.View notification;
	private AdvancedRemoteViews remoteViews;
	private AdvancedRemoteViews bigRemoteViews;
	private AdvancedRemoteViews remoteViewsNougat;
	private AdvancedRemoteViews bigRemoteViewsNougat;
	private NotificationManagerCompat baseManager;
	private TestableNotificationManager manager;
	private NotificationCompat.Builder builder;
	private Notification.Builder builder2;
	private PlayerNotificationMvp.Model model;
	private PlayerNotificationMvp.Presenter presenter;
	private PendingIntents pendingIntents;

	public PlayerNotificationFactory(Context context) {
		super(context);
	}

	public PlayerNotificationMvp.View getNotification() {
		if (notification == null) {
			if (Build.VERSION.SDK_INT < 24) {
				notification = new PlayerNotificationCompat(
						getNormalRemoteViews(),
						getBigRemoteViews(),
						getManager(),
						getBuilder(),
						getPendingIntents());
			} else {
				notification = new PlayerNotificationNougat(
						getContext(),
						getNormalRemoteViewsNougat(),
						getBigRemoteViewsNougat(),
						getManager(),
						getBuilder2(),
						getPendingIntents());
			}
		}
		return notification;
	}

	public PendingIntents getPendingIntents() {
		if (pendingIntents == null)
			pendingIntents = new PendingIntents(getContext());
		return pendingIntents;
	}

	public AdvancedRemoteViews getNormalRemoteViews() {
		if (remoteViews == null)
			remoteViews = new AdvancedRemoteViews(getContext(), R.layout.notification);
		return remoteViews;
	}

	public AdvancedRemoteViews getNormalRemoteViewsNougat() {
		if (remoteViewsNougat == null)
			remoteViewsNougat = new AdvancedRemoteViews(getContext(), R.layout.notification_nougat);
		return remoteViewsNougat;
	}

	public AdvancedRemoteViews getBigRemoteViews() {
		if (bigRemoteViews == null)
			bigRemoteViews = new AdvancedRemoteViews(getContext(), R.layout.notification_big);
		return bigRemoteViews;
	}

	public AdvancedRemoteViews getBigRemoteViewsNougat() {
		if (bigRemoteViewsNougat == null)
			bigRemoteViewsNougat = new AdvancedRemoteViews(getContext(), R.layout.notification_nougat_big);
		return bigRemoteViewsNougat;
	}

	public TestableNotificationManager getManager() {
		if (manager == null)
			manager = new TestableNotificationManager(getBaseManager());
		return manager;
	}

	public NotificationManagerCompat getBaseManager() {
		if (baseManager == null)
			baseManager = NotificationManagerCompat.from(getContext());
		return baseManager;
	}

	public NotificationCompat.Builder getBuilder() {
		if (builder == null)
			builder = new NotificationCompat.Builder(getContext());
		return builder;
	}

	public Notification.Builder getBuilder2() {
		if (builder2 == null)
			builder2 = new Notification.Builder(getContext());
		return builder2;
	}

	public PlayerNotificationMvp.Model getModel() {
		if (model == null)
			model = new PlayerNotificationModel(getContext(), getArtProvider());
		return model;
	}

	public PlayerNotificationMvp.Presenter getPresenter() {
		if (presenter == null)
			presenter = new PlayerNotificationPresenter(getModel(), getNotification());
		return presenter;
	}
}
