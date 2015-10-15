package com.edavtyan.materialplayer.app.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.os.IBinder;
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
import com.edavtyan.materialplayer.app.music.Metadata;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.services.MusicPlayerService.MusicPlayerBinder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.IOException;

public class NowPlayingActivity extends AppCompatActivity {
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
        artView = (ImageView) findViewById(R.id.nowplaying_art);
        artBackView = (ImageView) findViewById(R.id.nowplaying_art_back);

        Toolbar toolbar = (Toolbar) findViewById(R.id.nowplaying_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            FrameLayout artFrame = (FrameLayout) findViewById(R.id.nowplaying_art_frame);
            artFrame.getLayoutParams().height = getScreenSize().x;
        }

        playerConnection = new MusicPlayerConnection();
        Intent intent = new Intent(this, MusicPlayerService.class);
        bindService(intent, playerConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isBound) {
            syncTrackInfoWithService();
        }
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

    private String getTrackInfo(Metadata metadata) {
        return getResources().getString(
                R.string.nowplaying_info_pattern,
                metadata.getArtistTitle(),
                metadata.getAlbumTitle());
    }

    private void syncTrackInfoWithService() {
        Metadata metadata = playerService.getMetadata();
        titleView.setText(metadata.getTrackTitle());
        infoView.setText(getTrackInfo(metadata));

        RequestCreator artRequest = Picasso.with(this)
                .load(metadata.getArtFile())
                .placeholder(R.drawable.fallback_cover)
                .error(R.drawable.fallback_cover);
        artRequest.into(artView);
        artRequest.into(artBackView);

        if (playerService.isPlaying()) {
            playPauseImageButton.setImageResource(R.drawable.ic_pause_white_36dp);
        } else {
            playPauseImageButton.setImageResource(R.drawable.ic_play_white_36dp);
        }
    }
}
