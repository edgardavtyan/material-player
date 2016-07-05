package com.edavtyan.materialplayer;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.RemoteViews;

import com.edavtyan.materialplayer.utils.ArtProvider;
import com.edavtyan.materialplayer.components.tracks.Track;
import com.edavtyan.materialplayer.utils.PendingIntents;
import com.edavtyan.materialplayer.components.main.MainActivity;

public class NowPlayingNotification implements ServiceConnection {
	private static final int NOTIFICATION_ID = 1;

	private final NotificationCompat.Builder builder;
	private final NotificationManagerCompat manager;
	private final Context context;
	private MusicPlayerService service;


	public NowPlayingNotification(Context newContext) {
		context = newContext;
		manager = NotificationManagerCompat.from(context);
		builder = new NotificationCompat.Builder(context)
				.setSmallIcon(R.mipmap.ic_launcher)
				.setContent(buildView());
		context.bindService(
				new Intent(context, MusicPlayerService.class),
				this, Context.BIND_AUTO_CREATE);


		BroadcastReceiver newTrackReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				Notification notification = build();
				manager.notify(NOTIFICATION_ID, notification);
			}
		};

		context.registerReceiver(
				newTrackReceiver,
				new IntentFilter(MusicPlayerService.SEND_NEW_TRACK));
	}

	/*
	 * ServiceConnection
	 */

	@Override
	public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
		service = ((MusicPlayerService.MusicPlayerBinder) iBinder).getService();
		builder.setContent(buildView());
	}

	@Override
	public void onServiceDisconnected(ComponentName componentName) {
	}

	/*
	 * Methods
	 */

	public Notification build() {
		Notification notification = builder
				.setContent(buildView())
				.setSmallIcon(R.drawable.ic_status)
				.build();
		notification.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
		return notification;
	}

	private RemoteViews buildView() {
		Intent notificationIntent = new Intent(context, MainActivity.class);
		notificationIntent.setAction(Intent.ACTION_MAIN);
		notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);

		RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.notification);
		view.setOnClickPendingIntent(
				R.id.art,
				PendingIntents.fromIntent(context, notificationIntent));
		view.setOnClickPendingIntent(
				R.id.info_wrapper,
				PendingIntents.fromIntent(context, notificationIntent));
		view.setOnClickPendingIntent(
				R.id.play_pause,
				PendingIntents.getBroadcast(context, MusicPlayerService.ACTION_PLAY_PAUSE));
		view.setOnClickPendingIntent(
				R.id.fast_forward,
				PendingIntents.getBroadcast(context, MusicPlayerService.ACTION_FAST_FORWARD));
		view.setOnClickPendingIntent(
				R.id.rewind,
				PendingIntents.getBroadcast(context, MusicPlayerService.ACTION_REWIND));

		if (service != null && service.getQueue().hasData()) {
			Track track = service.getQueue().getCurrentTrack();
			view.setTextViewText(R.id.title, track.getTitle());
			view.setTextViewText(R.id.info, track.getAlbumTitle());

			String artPath = ArtProvider.fromTrack(track).getAbsolutePath();
			Bitmap art = BitmapFactory.decodeFile(artPath);
			if (art != null) {
				view.setImageViewBitmap(R.id.art, art);
			} else {
				view.setImageViewResource(R.id.art, R.drawable.fallback_cover_listitem);
			}
		}

		return view;
	}
}
