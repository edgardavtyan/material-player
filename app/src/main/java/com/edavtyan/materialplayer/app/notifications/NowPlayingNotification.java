package com.edavtyan.materialplayer.app.notifications;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.RemoteViews;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.MainActivity;
import com.edavtyan.materialplayer.app.music.Metadata;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.utils.PendingIntents;
import com.google.gson.Gson;

public class NowPlayingNotification {
    private static final int NOTIFICATION_ID = 1;

    private final NotificationCompat.Builder builder;
    private final NotificationManagerCompat manager;
    private final RemoteViews view;


    public NowPlayingNotification(Context context) {
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setAction(Intent.ACTION_MAIN);
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        view = new RemoteViews(context.getPackageName(), R.layout.notification);
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

        builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContent(view);

        manager = NotificationManagerCompat.from(context);

        BroadcastReceiver newTrackReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String jsonStr = intent.getStringExtra(MusicPlayerService.EXTRA_METADATA);
                Metadata metadata = new Gson().fromJson(jsonStr, Metadata.class);

                view.setTextViewText(R.id.title, metadata.getTrackTitle());
                view.setTextViewText(R.id.info, metadata.getAlbumTitle());
                view.setImageViewBitmap(R.id.art, BitmapFactory.decodeFile(metadata.getArtFile().getPath()));

                updateNotification();
            }
        };

        context.registerReceiver(
                newTrackReceiver,
                new IntentFilter(MusicPlayerService.SEND_NEW_TRACK));
    }


    public Notification build() {
        Notification notification = builder.build();
        notification.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
        return notification;
    }


    private void updateNotification() {
        manager.notify(NOTIFICATION_ID, builder.setContent(view).build());
    }
}
