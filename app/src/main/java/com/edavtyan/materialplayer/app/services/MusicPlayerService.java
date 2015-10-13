package com.edavtyan.materialplayer.app.services;

import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.List;

public class MusicPlayerService extends Service
implements MediaPlayer.OnPreparedListener {
    /* ********* */
    /* Constants */
    /* ********* */

    private final String TAG = "MusicPlayerService";

    /* ****** */
    /* Fields */
    /* ****** */

    private List<Integer> tracks;
    private MediaPlayer player;
    private int position;
    private boolean hasData;

    /* *************** */
    /* Service members */
    /* *************** */

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicPlayerBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
        player.setOnPreparedListener(this);
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
    }

    /* ************** */
    /* Public methods */
    /* ************** */

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
        return tracks.get(position);
    }
}
