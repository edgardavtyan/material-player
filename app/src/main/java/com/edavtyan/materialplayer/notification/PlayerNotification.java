package com.edavtyan.materialplayer.notification;

import android.app.Notification;
import android.graphics.Bitmap;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.service.PlayerService;
import com.edavtyan.materialplayer.lib.views.AdvancedRemoteViews;
import com.edavtyan.materialplayer.lib.testable.TestableNotificationManager;

import lombok.Getter;

public abstract class PlayerNotification {
	private static final int NOTIFICATION_ID = 1;

	private final AdvancedRemoteViews normalRemoteViews;
	private final AdvancedRemoteViews bigRemoteViews;
	private final TestableNotificationManager manager;

	private @Getter Notification notification;

	public PlayerNotification(
			AdvancedRemoteViews normalRemoteViews,
			AdvancedRemoteViews bigRemoteViews,
			TestableNotificationManager manager) {
		this.normalRemoteViews = normalRemoteViews;
		this.bigRemoteViews = bigRemoteViews;
		this.manager = manager;

		normalRemoteViews.setOnClickBroadcast(R.id.rewind, PlayerService.ACTION_REWIND);
		normalRemoteViews.setOnClickBroadcast(R.id.play_pause, PlayerService.ACTION_PLAY_PAUSE);
		normalRemoteViews.setOnClickBroadcast(R.id.fast_forward, PlayerService.ACTION_FAST_FORWARD);
		normalRemoteViews.setOnClickBroadcast(R.id.close, PlayerService.ACTION_CLOSE);

		bigRemoteViews.setOnClickBroadcast(R.id.rewind, PlayerService.ACTION_REWIND);
		bigRemoteViews.setOnClickBroadcast(R.id.play_pause, PlayerService.ACTION_PLAY_PAUSE);
		bigRemoteViews.setOnClickBroadcast(R.id.fast_forward, PlayerService.ACTION_FAST_FORWARD);
		bigRemoteViews.setOnClickBroadcast(R.id.close, PlayerService.ACTION_CLOSE);
	}

	protected void setNotification(Notification notification) {
		this.notification = notification;
	}

	public void setTitle(String title) {
		normalRemoteViews.setTextViewText(R.id.title, title);
		bigRemoteViews.setTextViewText(R.id.title, title);
	}

	public void setInfo(String artist, String album) {
		normalRemoteViews.setTextViewText(R.id.info, album);
		bigRemoteViews.setTextViewText(R.id.artist, artist);
		bigRemoteViews.setTextViewText(R.id.album, album);
	}

	public void setArt(Bitmap art) {
		if (art != null) {
			normalRemoteViews.setImageViewBitmap(R.id.art, art);
			bigRemoteViews.setImageViewBitmap(R.id.art, art);
		} else {
			normalRemoteViews.setImageViewResource(R.id.art, R.drawable.fallback_cover);
			bigRemoteViews.setImageViewResource(R.id.art, R.drawable.fallback_cover);
		}
	}

	public void setIsPlaying(boolean isPlaying) {
		if (isPlaying) {
			normalRemoteViews.setImageViewResource(R.id.play_pause, R.drawable.ic_pause);
			bigRemoteViews.setImageViewResource(R.id.play_pause, R.drawable.ic_pause);
		} else {
			normalRemoteViews.setImageViewResource(R.id.play_pause, R.drawable.ic_play);
			bigRemoteViews.setImageViewResource(R.id.play_pause, R.drawable.ic_play);
		}
	}

	public void update() {
		manager.notify(NOTIFICATION_ID, notification);
	}
}
