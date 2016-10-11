package com.edavtyan.materialplayer.components.player_notification;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.main.MainActivity;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.lib.AdvancedRemoteViews;
import com.edavtyan.materialplayer.lib.testable.TestableNotificationManager;
import com.edavtyan.materialplayer.utils.ArtProvider;
import com.edavtyan.materialplayer.utils.PendingIntents;

import java.io.File;

import lombok.Getter;

public class PlayerNotification implements PlayerNotificationMvp.View {
	public static final int NOTIFICATION_ID = 1;

	private final Context context;
	private final TestableNotificationManager manager;
	private final @Getter Notification notification;
	private PlayerNotificationMvp.Presenter presenter;

	public PlayerNotification(
			Context context,
			AdvancedRemoteViews remoteViews,
			TestableNotificationManager manager,
			NotificationCompat.Builder builder,
			PendingIntents pendingIntents
	) {
		this.context = context;
		this.manager = manager;
		this.notification = builder
				.setSmallIcon(R.drawable.ic_status)
				.setContentIntent(pendingIntents.getActivity(MainActivity.class))
				.setContent(remoteViews)
				.build();
	}

	@Override public void setTitle(String title) {
		notification.contentView.setTextViewText(R.id.title, title);
	}

	@Override public void setInfo(String artist, String album) {
		String info = context.getString(R.string.nowplaying_info_pattern, artist, album);
		notification.contentView.setTextViewText(R.id.info, info);
	}

	@Override public void setArt(Track track) {
		File artFile = ArtProvider.fromTrack(track);
		;
		Bitmap art = BitmapFactory.decodeFile(artFile.getAbsolutePath());

		if (art != null) {
			notification.contentView.setImageViewBitmap(R.id.art, art);
		} else {
			notification.contentView.setImageViewResource(R.id.art, R.drawable.fallback_cover_listitem);
		}
	}

	@Override public void setIsPlaying(boolean isPlaying) {
		if (isPlaying) {
			notification.contentView.setImageViewResource(R.id.play_pause, R.drawable.ic_pause);
		} else {
			notification.contentView.setImageViewResource(R.id.play_pause, R.drawable.ic_play);
		}
	}

	@Override public void update() {
		manager.notify(NOTIFICATION_ID, notification);
	}
}
