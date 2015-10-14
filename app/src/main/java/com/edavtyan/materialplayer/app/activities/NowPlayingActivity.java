package com.edavtyan.materialplayer.app.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.services.MusicPlayerService.MusicPlayerBinder;
import com.edavtyan.materialplayer.app.utils.AlbumArtUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;
import java.io.IOException;

public class NowPlayingActivity extends AppCompatActivity {
    /* ********* */
    /* Constants */
    /* ********* */

    public static final String EXTRA_TRACK_ALBUM_ID = "track_id";
    public static final String EXTRA_TRACK_TITLE = "track_title";
    public static final String EXTRA_TRACK_ARTIST = "track_artist";
    public static final String EXTRA_TRACK_ALBUM = "track_album";

    public static final Uri TRACKS_URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    public static final String[] TRACKS_PROJECTION = new String[] {
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ALBUM_ID
    };

    public static final int COLUMN_TITLE = 0;
    public static final int COLUMN_ARTIST = 1;
    public static final int COLUMN_ALBUM = 2;
    public static final int COLUMN_ALBUM_ID = 3;

    /* ****** */
    /* Fields */
    /* ****** */

    private MusicPlayerConnection playerConnection;
    private MusicPlayerService playerService;
    private ImageView playPauseImageButton;
    private boolean isBound;

    private TextView titleView;
    private TextView infoView;
    private ImageView artView;
    private ImageView artBackView;

    /* ************************* */
    /* AppCompatActivity members */
    /* ************************* */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowplaying);

        playPauseImageButton = (ImageButton) findViewById(R.id.nowplaying_control_playPause);
        infoView = (TextView) findViewById(R.id.nowplaying_info);
        titleView = (TextView) findViewById(R.id.nowplaying_title);

        Toolbar toolbar = (Toolbar) findViewById(R.id.nowplaying_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int albumId = getIntent().getIntExtra(EXTRA_TRACK_ALBUM_ID, 0);
        RequestCreator artRequest = Picasso
                .with(this)
                .load(AlbumArtUtils.getArtFileFromId(albumId, this))
                .placeholder(R.drawable.fallback_cover)
                .error(R.drawable.fallback_cover);

        artView = (ImageView) findViewById(R.id.nowplaying_art);
        artBackView = (ImageView) findViewById(R.id.nowplaying_art_back);
        artRequest.into(artView);
        artRequest.into(artBackView);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            FrameLayout artFrame = (FrameLayout) findViewById(R.id.nowplaying_art_frame);
            artFrame.getLayoutParams().height = getScreenSize().x;
        }

        playerConnection = new MusicPlayerConnection();
        Intent intent = new Intent(this, MusicPlayerService.class);
        bindService(intent, playerConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(playerConnection);
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /* ******* */
    /* Classes */
    /* ******* */

    private class MusicPlayerConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicPlayerBinder binder = (MusicPlayerBinder) iBinder;
            playerService = binder.getService();
            isBound = true;

            syncTrackInfoWithService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    }

    /* *********************************** */
    /* Playback controls onClick listeners */
    /* *********************************** */

    public void play(View view) throws IOException {
        if (playerService.isPlaying()) {
            playerService.pause();
            playPauseImageButton.setImageResource(R.drawable.ic_play_white_36dp);
        } else {
            playerService.resume();
            playPauseImageButton.setImageResource(R.drawable.ic_pause_white_36dp);
        }
    }

    public void fastForward(View view) throws IOException {
        playerService.moveNext();
        playerService.prepare();
        syncTrackInfoWithService();
    }

    public void rewind(View view) throws IOException {
        playerService.movePrev();
        playerService.prepare();
        syncTrackInfoWithService();
    }

    private Point getScreenSize() {
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        return screenSize;
    }

    /* *************** */
    /* Private methods */
    /* *************** */

    private String getTrackInfo(Cursor cursor) {
        return getResources().getString(
                R.string.nowplaying_info_pattern,
                cursor.getString(COLUMN_ARTIST),
                cursor.getString(COLUMN_ALBUM));
    }

    private void syncTrackInfoWithService() {
        Cursor trackCursor = null;
        try {
            int id = playerService.getCurrentTrackId();
            trackCursor = getContentResolver().query(
                    TRACKS_URI, TRACKS_PROJECTION,
                    MediaStore.Audio.Media._ID + "=" + id,
                    null, null);
            trackCursor.moveToFirst();
            titleView.setText(trackCursor.getString(COLUMN_TITLE));
            infoView.setText(getTrackInfo(trackCursor));

            int albumId = trackCursor.getInt(COLUMN_ALBUM_ID);
            File artFile = AlbumArtUtils.getArtFileFromId(albumId, this);
            RequestCreator artRequest = AlbumArtUtils.getFullArtRequest(this, artFile);
            artRequest.into(artView);
            artRequest.into(artBackView);

            if (playerService.isPlaying()) {
                playPauseImageButton.setImageResource(R.drawable.ic_pause_white_36dp);
            } else {
                playPauseImageButton.setImageResource(R.drawable.ic_play_white_36dp);
            }
        } finally {
            if (trackCursor != null) {
                trackCursor.close();
            }
        }

    }
}
