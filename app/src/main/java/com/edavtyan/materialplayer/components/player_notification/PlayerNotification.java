package com.edavtyan.materialplayer.components.player_notification;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.main.MainActivity;
import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.lib.AdvancedRemoteViews;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.lib.testable.TestableNotificationManager;
import com.edavtyan.materialplayer.utils.PendingIntents;

import lombok.Getter;

public class PlayerNotification implements PlayerNotificationMvp.View {
	public static final int NOTIFICATION_ID = 1;

	private final Context context;
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

	@Override public void setTitle(String title) {
		notification.contentView.setTextViewText(R.id.title, title);
		notification.bigContentView.setTextViewText(R.id.title, title);
	}

	@Override public void setInfo(String artist, String album) {
		String info = context.getString(R.string.nowplaying_info_pattern, artist, album);
		notification.contentView.setTextViewText(R.id.info, info);
		notification.bigContentView.setTextViewText(R.id.artist, artist);
		notification.bigContentView.setTextViewText(R.id.album, album);
	}

	@Override public void setArt(Bitmap art) {
		if (art != null) {
			notification.contentView.setImageViewBitmap(R.id.art, art);
			notification.bigContentView.setImageViewBitmap(R.id.art, art);
		} else {
			notification.contentView.setImageViewResource(R.id.art, R.drawable.fallback_cover);
			notification.bigContentView.setImageViewResource(R.id.art, R.drawable.fallback_cover);
		}
	}

	@Override public void setIsPlaying(boolean isPlaying) {
		if (isPlaying) {
			notification.contentView.setImageViewResource(R.id.play_pause, R.drawable.ic_pause);
			notification.bigContentView.setImageViewResource(R.id.play_pause, R.drawable.ic_pause);
		} else {
			notification.contentView.setImageViewResource(R.id.play_pause, R.drawable.ic_play);
			notification.bigContentView.setImageViewResource(R.id.play_pause, R.drawable.ic_play);
		}
	}

	@Override public void update() {
		manager.notify(NOTIFICATION_ID, notification);
	}
}
