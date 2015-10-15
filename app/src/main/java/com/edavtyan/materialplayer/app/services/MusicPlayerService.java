package com.edavtyan.materialplayer.app.services;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
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
import com.edavtyan.materialplayer.app.activities.MainActivity;
import com.edavtyan.materialplayer.app.activities.NowPlayingActivity;
import com.edavtyan.materialplayer.app.music.Metadata;
import com.edavtyan.materialplayer.app.utils.AlbumArtUtils;
import com.edavtyan.materialplayer.app.utils.PendingIntents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicPlayerService extends Service
implements MediaPlayer.OnPreparedListener {
    /* ********* */
    /* Constants */
    /* ********* */

    private final String TAG = "MusicPlayerService";

    private final Uri TRACKS_URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    private final String[] TRACKS_PROJECTION = new String[] {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
    };

    private static final int COLUMN_ALBUM_ID = 1;
    private static final int COLUMN_TITLE = 2;
    private static final int COLUMN_DURATION = 3;
    private static final int COLUMN_ARTIST = 4;
    private static final int COLUMN_ALBUM = 5;

    private static final String COLUMN_NAME_ID = MediaStore.Audio.Media._ID;

    private static final int NOTIFICATION_ID = 1;

    private static final String ACTION_PLAY_PAUSE = "com.edavtyan.materialplayer.app.playpause";
    private static final String ACTION_FAST_FORWARD = "com.edavtyan.materialplayer.app.fastforward";

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
            try {
                moveNext();
                prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        initMetadata();

        notificationBuilder.setContent(getNotificationLayout());
        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notificationBuilder.build());
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

    public void prepare() throws IOException {
        if (hasData) {
            player.reset();
        }

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        uri = ContentUris.withAppendedId(uri, tracks.get(position));
        player.setDataSource(getApplicationContext(), uri);
        player.prepareAsync();
    }

    public void resume() {
        if (hasData) {
            player.start();
        }
    }

    public void pause() {
        if (hasData) {
            player.pause();
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

    private void initMetadata() {
        metadata = new Metadata();

        Cursor tracksCursor = null;
        try {
            int trackId = getCurrentTrackId();
            metadata.setTrackId(trackId);

            tracksCursor = getContentResolver().query(
                    TRACKS_URI, TRACKS_PROJECTION,
                    COLUMN_NAME_ID + "=" + trackId,
                    null, null);
            tracksCursor.moveToFirst();
            metadata.setTrackTitle(tracksCursor.getString(COLUMN_TITLE));
            metadata.setDuration(tracksCursor.getLong(COLUMN_DURATION));
            metadata.setAlbumId(tracksCursor.getInt(COLUMN_ALBUM_ID));
            metadata.setArtistTitle(tracksCursor.getString(COLUMN_ARTIST));
            metadata.setAlbumTitle(tracksCursor.getString(COLUMN_ALBUM));
            metadata.setArtFile(AlbumArtUtils.getArtFileFromId(metadata.getAlbumId(), this));
        } finally {
            if (tracksCursor != null) {
                tracksCursor.close();
            }
        }
    }

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
