package com.edavtyan.materialplayer.app.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.connections.MusicPlayerConnection;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;
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

    /* ****** */
    /* Fields */
    /* ****** */

    private MusicPlayerConnection playerConnection;
    private boolean isBound;

    /* ************************* */
    /* AppCompatActivity members */
    /* ************************* */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowplaying);

        Toolbar toolbar = (Toolbar) findViewById(R.id.nowplaying_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView infoView = (TextView) findViewById(R.id.nowplaying_info);
        infoView.setText(getTrackInfo());

        TextView titleView = (TextView) findViewById(R.id.nowplaying_title);
        titleView.setText(getIntent().getStringExtra(EXTRA_TRACK_TITLE));

        int albumId = getIntent().getIntExtra(EXTRA_TRACK_ALBUM_ID, 0);
        File albumPath = new File(AlbumArtUtils.getArtPathFromId(albumId, this));
        RequestCreator artRequest = Picasso.with(this)
                .load(albumPath)
                .placeholder(R.drawable.fallback_cover)
                .error(R.drawable.fallback_cover);

        ImageView artView = (ImageView) findViewById(R.id.nowplaying_art);
        ImageView artBackView = (ImageView) findViewById(R.id.nowplaying_art_back);
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

    /* *********************************** */
    /* Playback controls onClick listeners */
    /* *********************************** */

    public void play(View view) throws IOException {
        if (playerConnection.getService().isPlaying()) {
            playerConnection.getService().pause();
        } else {
            playerConnection.getService().resume();
        }
    }

    public void fastForward(View view) throws IOException {
        playerConnection.getService().moveNext();
        playerConnection.getService().prepare();
    }

    public void rewind(View view) throws IOException {
        playerConnection.getService().movePrev();
        playerConnection.getService().prepare();
    }

    private Point getScreenSize() {
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        return screenSize;
    }

    /* *************** */
    /* Private methods */
    /* *************** */

    private String getTrackInfo() {
        return getResources().getString(
                    R.string.nowplaying_info_pattern,
                    getIntent().getStringExtra(EXTRA_TRACK_ARTIST),
                    getIntent().getStringExtra(EXTRA_TRACK_ALBUM));
    }
}
