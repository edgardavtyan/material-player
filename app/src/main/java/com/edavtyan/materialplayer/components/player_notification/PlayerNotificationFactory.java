package com.edavtyan.materialplayer.components.player_notification;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.edavtyan.materialplayer.lib.AdvancedRemoteViews;
import com.edavtyan.materialplayer.lib.base.BaseFactory;
import com.edavtyan.materialplayer.lib.testable.TestableNotificationManager;
import com.edavtyan.materialplayer.utils.PendingIntents;

public class PlayerNotificationFactory extends BaseFactory {

	private final int normalLayoutId;
	private final int bigLayoutId;
	private PlayerNotificationMvp.View notification;
	private AdvancedRemoteViews remoteViews;
	private AdvancedRemoteViews bigRemoteViews;
	private NotificationManagerCompat baseManager;
	private TestableNotificationManager manager;
	private NotificationCompat.Builder builder;
	private PlayerNotificationMvp.Model model;
	private PlayerNotificationMvp.Presenter presenter;
	private PendingIntents pendingIntents;

	public PlayerNotificationFactory(Context context, int normalLayoutId, int bigLayoutId) {
		super(context);
		this.normalLayoutId = normalLayoutId;
		this.bigLayoutId = bigLayoutId;
	}

	public PlayerNotificationMvp.View getNotification() {
		if (notification == null)
			notification = new PlayerNotification(
					getContext(),
					getNormalRemoteViews(),
					getBigRemoteViews(),
					getManager(),
					getBuilder(),
					getPendingIntents());
		return notification;
	}

	public PendingIntents getPendingIntents() {
		if (pendingIntents == null)
			pendingIntents = new PendingIntents(getContext());
		return pendingIntents;
	}

	public AdvancedRemoteViews getNormalRemoteViews() {
		if (remoteViews == null)
			remoteViews = new AdvancedRemoteViews(getContext(), normalLayoutId);
		return remoteViews;
	}

	public AdvancedRemoteViews getBigRemoteViews() {
		if (bigRemoteViews == null)
			bigRemoteViews = new AdvancedRemoteViews(getContext(), bigLayoutId);
		return bigRemoteViews;
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
