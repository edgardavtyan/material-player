package com.edavtyan.materialplayer.components.player_notification;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.main.MainActivity;
import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.lib.AdvancedRemoteViews;
import com.edavtyan.materialplayer.lib.testable.TestableNotificationManager;
import com.edavtyan.materialplayer.utils.PendingIntents;

import lombok.Getter;

public class PlayerNotification implements PlayerNotificationMvp.View {
	public static final int NOTIFICATION_ID = 1;

	private final Context context;
	private final AdvancedRemoteViews normalRemoteViews;
	private final AdvancedRemoteViews bigRemoteViews;
	private final TestableNotificationManager manager;
	private final @Getter Notification notification;

	public PlayerNotification(
			Context context,
			AdvancedRemoteViews normalRemoteViews,
			AdvancedRemoteViews bigRemoteViews,
			TestableNotificationManager manager,
			NotificationCompat.Builder builder,
			PendingIntents pendingIntents
	) {
		this.context = context;
		this.normalRemoteViews = normalRemoteViews;
		this.bigRemoteViews = bigRemoteViews;
		this.manager = manager;
		this.notification = builder
				.setSmallIcon(R.drawable.ic_status)
				.setContentIntent(pendingIntents.getActivity(MainActivity.class))
				.setContent(normalRemoteViews)
				.setCustomBigContentView(bigRemoteViews)
				.setPriority(NotificationCompat.PRIORITY_MAX)
				.build();

		normalRemoteViews.setOnClickBroadcast(R.id.rewind, PlayerService.ACTION_REWIND);
		normalRemoteViews.setOnClickBroadcast(R.id.play_pause, PlayerService.ACTION_PLAY_PAUSE);
		normalRemoteViews.setOnClickBroadcast(R.id.fast_forward, PlayerService.ACTION_FAST_FORWARD);

		bigRemoteViews.setOnClickBroadcast(R.id.rewind, PlayerService.ACTION_REWIND);
		bigRemoteViews.setOnClickBroadcast(R.id.play_pause, PlayerService.ACTION_PLAY_PAUSE);
		bigRemoteViews.setOnClickBroadcast(R.id.fast_forward, PlayerService.ACTION_FAST_FORWARD);
	}

	@Override
	public void setTitle(String title) {
		normalRemoteViews.setTextViewText(R.id.title, title);
		bigRemoteViews.setTextViewText(R.id.title, title);
	}

	@Override
	public void setInfo(String artist, String album) {
		String info = context.getString(R.string.nowplaying_info_pattern, artist, album);
		normalRemoteViews.setTextViewText(R.id.info, info);
		bigRemoteViews.setTextViewText(R.id.artist, artist);
		bigRemoteViews.setTextViewText(R.id.album, album);
	}

	@Override
	public void setArt(Bitmap art) {
		if (art != null) {
			normalRemoteViews.setImageViewBitmap(R.id.art, art);
			bigRemoteViews.setImageViewBitmap(R.id.art, art);
		} else {
			normalRemoteViews.setImageViewResource(R.id.art, R.drawable.fallback_cover);
			bigRemoteViews.setImageViewResource(R.id.art, R.drawable.fallback_cover);
		}
	}

	@Override
	public void setIsPlaying(boolean isPlaying) {
		if (isPlaying) {
			normalRemoteViews.setImageViewResource(R.id.play_pause, R.drawable.ic_pause);
			bigRemoteViews.setImageViewResource(R.id.play_pause, R.drawable.ic_pause);
		} else {
			normalRemoteViews.setImageViewResource(R.id.play_pause, R.drawable.ic_play);
			bigRemoteViews.setImageViewResource(R.id.play_pause, R.drawable.ic_play);
		}
	}

	@Override
	public void update() {
		manager.notify(NOTIFICATION_ID, notification);
	}
}
