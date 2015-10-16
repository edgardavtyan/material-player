package com.edavtyan.materialplayer.app.notifications;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.RemoteViews;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.NowPlayingActivity;
import com.edavtyan.materialplayer.app.music.Metadata;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.utils.PendingIntents;
import com.google.gson.Gson;

public class NowPlayingNotification {
    private static final int NOTIFICATION_ID = 1;

    private NotificationCompat.Builder builder;
    private NotificationManagerCompat manager;
    private RemoteViews view;

    private BroadcastReceiver playReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            view.setImageViewResource(
                    R.id.playPause,
                    R.drawable.ic_pause_black_36dp);
            updateNotification();
        }
    };
    private BroadcastReceiver pauseReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            view.setImageViewResource(
                    R.id.playPause,
                    R.drawable.ic_play_black_36dp);
            updateNotification();
        }
    };
    private BroadcastReceiver newTrackReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String jsonStr = intent.getStringExtra(MusicPlayerService.EXTRA_METADATA);
            Metadata metadata = new Gson().fromJson(jsonStr, Metadata.class);

            view.setTextViewText(R.id.title, metadata.getTrackTitle());
            view.setTextViewText(R.id.info, metadata.getAlbumTitle());

            if (metadata.getArtFile() != null) {
                Bitmap art = BitmapFactory.decodeFile(metadata.getArtFile().getPath());
                view.setImageViewBitmap(R.id.art, art);
            } else {
                view.setImageViewResource(R.id.art, R.drawable.fallback_cover);
            }

            updateNotification();
        }
    };


    public NowPlayingNotification(Context context) {
        view = new RemoteViews(context.getPackageName(), R.layout.notification);
        view.setOnClickPendingIntent(
                R.id.art,
                PendingIntents.getActivity(context, NowPlayingActivity.class));
        view.setOnClickPendingIntent(
                R.id.title,
                PendingIntents.getActivity(context, NowPlayingActivity.class));
        view.setOnClickPendingIntent(
                R.id.playPause,
                PendingIntents.getBroadcast(context, MusicPlayerService.ACTION_PLAY_PAUSE));
        view.setOnClickPendingIntent(
                R.id.fast_forward,
                PendingIntents.getBroadcast(context, MusicPlayerService.ACTION_FAST_FORWARD));

        builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContent(view);

        manager = NotificationManagerCompat.from(context);

        context.registerReceiver(
                playReceiver,
                new IntentFilter(MusicPlayerService.SEND_PLAY));
        context.registerReceiver(
                pauseReceiver,
                new IntentFilter(MusicPlayerService.SEND_PAUSE));
        context.registerReceiver(
                newTrackReceiver,
                new IntentFilter(MusicPlayerService.SEND_NEW_TRACK));
    }


    public Notification build() {
        return builder.build();
    }


    private void updateNotification() {
        manager.notify(NOTIFICATION_ID, builder.setContent(view).build());
    }
}
