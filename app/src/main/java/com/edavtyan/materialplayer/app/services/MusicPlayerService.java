package com.edavtyan.materialplayer.app.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.NowPlayingActivity;
import com.edavtyan.materialplayer.app.music.Metadata;
import com.edavtyan.materialplayer.app.utils.PendingIntents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// TODO: This needs some serious refactoring
public class MusicPlayerService extends Service
implements MediaPlayer.OnPreparedListener {
    /* ********* */
    /* Constants */
    /* ********* */

    private final String TAG = "MusicPlayerService";

    private static final int NOTIFICATION_ID = 1;

    public static final String ACTION_PLAY_PAUSE = "com.edavtyan.materialplayer.app.playpause";
    public static final String ACTION_FAST_FORWARD = "com.edavtyan.materialplayer.app.fastforward";
    public static final String ACTION_REWIND = "com.edavtyan.materialplayer.app.rewind";

    public static final String SEND_PLAY = "com.edavtyan.materialplayer.app.play";
    public static final String SEND_PAUSE = "com.edavtyan.materialplayer.app.pause";
    public static final String SEND_NEW_TRACK = "com.edavtyan.materialplayer.app.newTrack";

    /* ****** */
    /* Fields */
    /* ****** */

    private List<Integer> tracks;
    private MediaPlayer player;
    private Metadata metadata;
    private NotificationCompat.Builder notificationBuilder;
    private int position;
    private boolean hasData;

    /* ******************* */
    /* Broadcast Receivers */
    /* ******************* */

    private class PlayPauseReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (isPlaying()) {
                pause();
            } else {
                resume();
            }

            notificationBuilder.setContent(getNotificationLayout());
            NotificationManagerCompat
                    .from(MusicPlayerService.this)
                    .notify(NOTIFICATION_ID, notificationBuilder.build());
        }
    }

    private class FastForwardReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            moveNext();
            prepare();
        }
    }

    private class RewindReciever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            movePrev();
            prepare();
        }
    }

    /* *************** */
    /* Service members */
    /* *************** */

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicPlayerBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContent(new RemoteViews(getPackageName(), R.layout.notification));

        startForeground(NOTIFICATION_ID, notificationBuilder.build());
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        player = new MediaPlayer();
        player.setOnPreparedListener(this);

        tracks = new ArrayList<>();

        IntentFilter playPauseFilter = new IntentFilter(ACTION_PLAY_PAUSE);
        registerReceiver(new PlayPauseReceiver(), playPauseFilter);

        IntentFilter fastForwardFilter = new IntentFilter(ACTION_FAST_FORWARD);
        registerReceiver(new FastForwardReceiver(), fastForwardFilter);

        IntentFilter rewindFilter = new IntentFilter(ACTION_REWIND);
        registerReceiver(new RewindReciever(), rewindFilter);
    }

    public class MusicPlayerBinder extends Binder {
        public MusicPlayerService getService() {
            return MusicPlayerService.this;
        }
    }

    /* ******************* */
    /* MediaPlayer members */
    /* ******************* */

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        player.start();
        hasData = true;
        metadata = Metadata.fromId(getCurrentTrackId(), this);

        notificationBuilder.setContent(getNotificationLayout());
        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notificationBuilder.build());

        sendBroadcast(new Intent(SEND_NEW_TRACK));
    }

    /* ************** */
    /* Public methods */
    /* ************** */

    public Metadata getMetadata() {
        return metadata;
    }

    public void setTracks(List<Integer> tracks) {
        this.tracks = tracks;
        Log.d(TAG, "setTracks count=" + this.tracks.size());
    }

    public void setPosition(int position) {
        this.position = position;
        Log.d(TAG, "setPosition position=" + this.position);
    }

    public void prepare() {
        if (hasData) {
            player.reset();
        }

        try {
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            uri = ContentUris.withAppendedId(uri, tracks.get(position));
            player.setDataSource(getApplicationContext(), uri);
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        if (hasData) {
            player.start();
            notificationBuilder.setContent(getNotificationLayout());
            NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notificationBuilder.build());
            sendBroadcast(new Intent(SEND_PLAY));
        }
    }

    public void pause() {
        if (hasData) {
            player.pause();
            notificationBuilder.setContent(getNotificationLayout());
            NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notificationBuilder.build());
            sendBroadcast(new Intent(SEND_PAUSE));
        }
    }

    public boolean isPlaying() {
        return player.isPlaying();
    }

    public void moveNext() {
        if (position >= tracks.size() - 1) {
            position = 0;
        } else {
            position++;
        }
    }

    public void movePrev() {
        if (player.getCurrentPosition() > 5000) {
            player.seekTo(0);
        } else {
            if (position != 0) {
                position--;
            } else {
                position = tracks.size() - 1;
            }
        }
    }

    public int getCurrentTrackId() {
        if (hasData()) {
            return tracks.get(position);
        } else {
            return -1;
        }
    }

    public boolean hasData() {
        return tracks.size() > 0;
    }

    /* *************** */
    /* Private methods */
    /* *************** */

    private RemoteViews getNotificationLayout() {
        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.notification);
        notificationLayout.setTextViewText(R.id.notification_title, metadata.getTrackTitle());
        notificationLayout.setTextViewText(R.id.notification_info, metadata.getAlbumTitle());

        if (metadata.getArtFile() != null) {
            Bitmap art = BitmapFactory.decodeFile(metadata.getArtFile().getPath());
            notificationLayout.setImageViewBitmap(R.id.notification_art, art);
        } else {
            notificationLayout.setImageViewResource(R.id.notification_art, R.drawable.fallback_cover);
        }

        if (isPlaying()) {
            notificationLayout.setImageViewResource(
                    R.id.notification_playPause, R.drawable.ic_pause_black_36dp);
        } else {
            notificationLayout.setImageViewResource(
                    R.id.notification_playPause, R.drawable.ic_play_black_36dp);
        }

        notificationLayout.setOnClickPendingIntent(
                R.id.notification_playPause,
                PendingIntents.getBroadcast(this, ACTION_PLAY_PAUSE));

        notificationLayout.setOnClickPendingIntent(
                R.id.notification_fastForward,
                PendingIntents.getBroadcast(this, ACTION_FAST_FORWARD));

        notificationLayout.setOnClickPendingIntent(
                R.id.notification_art,
                PendingIntents.getResumeActivity(this, NowPlayingActivity.class));

        notificationLayout.setOnClickPendingIntent(
                R.id.notification_info_container,
                PendingIntents.getResumeActivity(this, NowPlayingActivity.class));

        return notificationLayout;
    }
}
