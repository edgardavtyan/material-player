package com.edavtyan.materialplayer.app.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.edavtyan.materialplayer.app.music.Metadata;
import com.edavtyan.materialplayer.app.music.MusicPlayer;
import com.edavtyan.materialplayer.app.music.RepeatMode;
import com.edavtyan.materialplayer.app.notifications.NowPlayingNotification;

import java.util.List;

public class MusicPlayerService extends Service implements MediaPlayer.OnPreparedListener {
    private static final int NOTIFICATION_ID = 1;

    public static final String ACTION_PLAY_PAUSE = "com.edavtyan.materialplayer.app.playpause";
    public static final String ACTION_FAST_FORWARD = "com.edavtyan.materialplayer.app.fastforward";
    public static final String ACTION_REWIND = "com.edavtyan.materialplayer.app.rewind";

    public static final String SEND_PLAY = "com.edavtyan.materialplayer.app.play";
    public static final String SEND_PAUSE = "com.edavtyan.materialplayer.app.pause";
    public static final String SEND_NEW_TRACK = "com.edavtyan.materialplayer.app.newTrack";

    /*
     * Fields
     */

    private NowPlayingNotification notification;
    private MusicPlayer player;

    /*
     * Broadcast Receivers
     */

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
            if (hasData()) {
                moveNext();
                prepare();
            }
        }
    }

    private class RewindReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (hasData()) {
                movePrev();
                prepare();
            }
        }
    }

    /*
     * MusicPlayer
     */

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        sendBroadcast(new Intent(SEND_NEW_TRACK));
        seekTo(0);
    }

    /*
     * Service
     */

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

        notification = new NowPlayingNotification(this);
        player = new MusicPlayer(this);
        player.setOnPreparedListener(this);

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

    /*
     * Public methods
     */

    public Metadata getCurrentTrack() {
        return player.getCurrentTrack();
    }

    public List<Metadata> getTracks() {
        return player.getTracks();
    }

    public void setTracks(List<Metadata> tracks) {
        player.setTracks(tracks);
    }

    public RepeatMode getRepeatMode() {
        return player.getRepeatMode();
    }

    public void toggleRepeatMode() {
        player.toggleRepeatMode();
    }

    public void setCurrentIndex(int index) {
        player.setCurrentTrackIndex(index);
    }

    public void seekTo(int position) {
        player.seekTo(position);
    }

    public int getSeek() {
        return player.getSeek();
    }

    public int getDuration() {
        return player.getDuration();
    }

    public void prepare() {
        player.prepare();
    }

    public void resume() {
        player.resume();
        sendBroadcast(new Intent(SEND_PLAY));
    }

    public void pause() {
        player.pause();
        sendBroadcast(new Intent(SEND_PAUSE));
    }

    public boolean isPlaying() {
        return player.isPlaying();
    }

    public void moveNext() {
        player.moveNext();
    }

    public void movePrev() {
        player.movePrev();
    }

    public boolean hasData() {
        return player.getTracks().size() > 0;
    }
}
