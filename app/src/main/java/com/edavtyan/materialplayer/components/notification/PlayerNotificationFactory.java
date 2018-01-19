package com.edavtyan.materialplayer.components.notification;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayerNotificationFactory {
	@Provides
	@Singleton
	public PlayerNotification providePlayerNotification(
			PlayerNotificationCompat notificationCompat,
			@Nullable PlayerNotificationNougat notificationNougat) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
			return notificationCompat;
		} else {
			return notificationNougat;
		}
	}

	@Provides
	@Singleton
	public PlayerNotificationPresenter providePresenter(
			PlayerNotificationModel model, PlayerNotification notification) {
		return new PlayerNotificationPresenter(model, notification);
	}

	@Provides
	@Singleton
	public PlayerNotificationModel provideModel(
			Context context, AlbumArtProvider albumArtProvider) {
		return new PlayerNotificationModel(context, albumArtProvider);
	}
}
