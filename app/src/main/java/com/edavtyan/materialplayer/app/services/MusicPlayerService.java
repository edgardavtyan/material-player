package com.edavtyan.materialplayer.app.services;

import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;

import com.edavtyan.materialplayer.app.music.Metadata;
import com.edavtyan.materialplayer.app.utils.AlbumArtUtils;

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

    private static final String COLUMN_NAME_ID = MediaStore.Audio.Media._ID;

    /* ****** */
    /* Fields */
    /* ****** */

    private List<Integer> tracks;
    private MediaPlayer player;
    private Metadata metadata;
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

        tracks = new ArrayList<>();
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
            metadata.setArtFile(AlbumArtUtils.getArtFileFromId(metadata.getAlbumId(), this));
        } finally {
            if (tracksCursor != null) {
                tracksCursor.close();
            }
        }
    }
}
