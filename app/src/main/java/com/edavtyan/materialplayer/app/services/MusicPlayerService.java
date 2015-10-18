package com.edavtyan.materialplayer.app.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.app.music.Metadata;
import com.edavtyan.materialplayer.app.notifications.NowPlayingNotification;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// TODO: This needs some serious refactoring
public class MusicPlayerService extends Service
implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    /* ********* */
    /* Constants */
    /* ********* */
    private static final int NOTIFICATION_ID = 1;

    public static final String ACTION_PLAY_PAUSE = "com.edavtyan.materialplayer.app.playpause";
    public static final String ACTION_FAST_FORWARD = "com.edavtyan.materialplayer.app.fastforward";
    public static final String ACTION_REWIND = "com.edavtyan.materialplayer.app.rewind";

    public static final String SEND_PLAY = "com.edavtyan.materialplayer.app.play";
    public static final String SEND_PAUSE = "com.edavtyan.materialplayer.app.pause";
    public static final String SEND_NEW_TRACK = "com.edavtyan.materialplayer.app.newTrack";

    public static final String EXTRA_METADATA = "metadata";

    /* ****** */
    /* Fields */
    /* ****** */

    private List<Integer> tracks;
    private MediaPlayer player;
    private Metadata metadata;
    private NowPlayingNotification notification;
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
        }
    }

    private class FastForwardReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            moveNext();
            prepare();
        }
    }

    private class RewindReceiver extends BroadcastReceiver {
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
        startForeground(NOTIFICATION_ID, notification.build());
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        player = new MediaPlayer();
        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);

        tracks = new ArrayList<>();
        notification = new NowPlayingNotification(this);

        IntentFilter playPauseFilter = new IntentFilter(ACTION_PLAY_PAUSE);
        registerReceiver(new PlayPauseReceiver(), playPauseFilter);

        IntentFilter fastForwardFilter = new IntentFilter(ACTION_FAST_FORWARD);
        registerReceiver(new FastForwardReceiver(), fastForwardFilter);

        IntentFilter rewindFilter = new IntentFilter(ACTION_REWIND);
        registerReceiver(new RewindReceiver(), rewindFilter);
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

        String metadataJson = new Gson().toJson(metadata);
        sendBroadcast(new Intent(SEND_NEW_TRACK).putExtra(EXTRA_METADATA, metadataJson));
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        moveNext();
        prepare();
    }

    /* ************** */
    /* Public methods */
    /* ************** */

    public Metadata getMetadata() {
        return metadata;
    }

    public void setTracks(List<Integer> tracks) {
        this.tracks = tracks;
    }

    public void setCurrentIndex(int index) {
        this.position = index;
    }

    public void setPosition(int position) {
        player.seekTo(position);
    }

    public int getPosition() {
        return player.getCurrentPosition();
    }

    public int getDuration() {
        return player.getDuration();
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
            sendBroadcast(new Intent(SEND_PLAY));
        }
    }

    public void pause() {
        if (hasData) {
            player.pause();
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
}
