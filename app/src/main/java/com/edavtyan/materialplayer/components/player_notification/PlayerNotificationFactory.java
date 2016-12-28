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

	public PlayerNotificationMvp.View provideNotification() {
		if (notification == null)
			notification = new PlayerNotification(
					provideContext(),
					provideNormalRemoteViews(),
					provideBigRemoteViews(),
					provideManager(),
					provideBuilder(),
					providePendingIntents(),
					provideBitmapFactory());
		return notification;
	}

	public PendingIntents providePendingIntents() {
		if (pendingIntents == null)
			pendingIntents = new PendingIntents(provideContext());
		return pendingIntents;
	}

	public AdvancedRemoteViews provideNormalRemoteViews() {
		if (remoteViews == null)
			remoteViews = new AdvancedRemoteViews(provideContext(), normalLayoutId);
		return remoteViews;
	}

	public AdvancedRemoteViews provideBigRemoteViews() {
		if (bigRemoteViews == null)
			bigRemoteViews = new AdvancedRemoteViews(provideContext(), bigLayoutId);
		return bigRemoteViews;
	}

	public TestableNotificationManager provideManager() {
		if (manager == null)
			manager = new TestableNotificationManager(provideBaseManager());
		return manager;
	}

	public NotificationManagerCompat provideBaseManager() {
		if (baseManager == null)
			baseManager = NotificationManagerCompat.from(provideContext());
		return baseManager;
	}

	public NotificationCompat.Builder provideBuilder() {
		if (builder == null)
			builder = new NotificationCompat.Builder(provideContext());
		return builder;
	}

	public PlayerNotificationMvp.Model provideModel() {
		if (model == null)
			model = new PlayerNotificationModel(provideContext(), provideArtProvider());
		return model;
	}

	public PlayerNotificationMvp.Presenter providePresenter() {
		if (presenter == null)
			presenter = new PlayerNotificationPresenter(provideModel(), provideNotification());
		return presenter;
	}
}
