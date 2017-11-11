package com.edavtyan.materialplayer.components.player_notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.player.PlayerService;
import com.edavtyan.materialplayer.lib.AdvancedRemoteViews;
import com.edavtyan.materialplayer.lib.testable.TestableNotificationManager;
import com.edavtyan.materialplayer.utils.PendingIntents;

import lombok.Getter;

import static com.edavtyan.materialplayer.components.player_notification.PlayerNotification.NOTIFICATION_ID;

@TargetApi(24)
public class PlayerNotification2 implements PlayerNotificationMvp.View {
	private final AdvancedRemoteViews normalRemoteViews;
	private final AdvancedRemoteViews bigRemoteViews;
	private final TestableNotificationManager manager;
	private final @Getter Notification notification;

	public PlayerNotification2(
			Context context,
			AdvancedRemoteViews normalRemoteViews,
			AdvancedRemoteViews bigRemoteViews,
			TestableNotificationManager manager,
			Notification.Builder builder,
			PendingIntents pendingIntents) {
		this.normalRemoteViews = normalRemoteViews;
		this.bigRemoteViews = bigRemoteViews;
		this.manager = manager;

		this.notification = builder
				.setSmallIcon(R.drawable.ic_status)
				.setCustomContentView(normalRemoteViews)
				.setCustomBigContentView(bigRemoteViews)
				.setStyle(new Notification.DecoratedMediaCustomViewStyle())
				.setColor(ContextCompat.getColor(context, R.color.primary_orange))
				.build();

		normalRemoteViews.setOnClickBroadcast(R.id.rewind, PlayerService.ACTION_REWIND);
		normalRemoteViews.setOnClickBroadcast(R.id.play_pause, PlayerService.ACTION_PLAY_PAUSE);
		normalRemoteViews.setOnClickBroadcast(R.id.fast_forward, PlayerService.ACTION_FAST_FORWARD);
		normalRemoteViews.setOnClickBroadcast(R.id.close, PlayerService.ACTION_CLOSE);

		bigRemoteViews.setOnClickBroadcast(R.id.rewind, PlayerService.ACTION_REWIND);
		bigRemoteViews.setOnClickBroadcast(R.id.play_pause, PlayerService.ACTION_PLAY_PAUSE);
		bigRemoteViews.setOnClickBroadcast(R.id.fast_forward, PlayerService.ACTION_FAST_FORWARD);
		bigRemoteViews.setOnClickBroadcast(R.id.close, PlayerService.ACTION_CLOSE);
	}

	@Override
	public void setTitle(String title) {
		normalRemoteViews.setTextViewText(R.id.title, title);
		bigRemoteViews.setTextViewText(R.id.title, title);
	}

	@Override
	public void setInfo(String artist, String album) {
		normalRemoteViews.setTextViewText(R.id.info, album);
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
