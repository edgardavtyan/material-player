package com.edavtyan.materialplayer.app.activities;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.music.ArtProvider;
import com.edavtyan.materialplayer.app.music.Metadata;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.services.MusicPlayerService.MusicPlayerBinder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

public class NowPlayingActivity extends AppCompatActivity {
    /* ****** */
    /* Fields */
    /* ****** */

    private ArtProvider artProvider;

    private MusicPlayerConnection playerConnection;
    private MusicPlayerService playerService;
    private boolean isBound;

    private TextView titleView;
    private TextView infoView;
    private ImageView artView;
    private ImageView artBackView;

    private BroadcastReceiver newTrackReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            syncTrackInfoWithService();
        }
    };

    /* ************************* */
    /* AppCompatActivity members */
    /* ************************* */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowplaying);

        artProvider = new ArtProvider(this);

        infoView = (TextView) findViewById(R.id.info);
        titleView = (TextView) findViewById(R.id.title);
        artView = (ImageView) findViewById(R.id.art);
        artBackView = (ImageView) findViewById(R.id.art_back);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            FrameLayout artFrame = (FrameLayout) findViewById(R.id.art_frame);
            artFrame.getLayoutParams().height = getScreenSize().x;
        }

        playerConnection = new MusicPlayerConnection();
        Intent intent = new Intent(this, MusicPlayerService.class);
        bindService(intent, playerConnection, Context.BIND_AUTO_CREATE);

        registerReceiver(newTrackReceiver, new IntentFilter(MusicPlayerService.SEND_NEW_TRACK));
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
        unregisterReceiver(newTrackReceiver);
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

    /* *************** */
    /* Private methods */
    /* *************** */

    private Point getScreenSize() {
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        return screenSize;
    }

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

        new ArtLoadTask().execute(metadata);
    }

    private class ArtLoadTask extends AsyncTask<Metadata, Void, File> {
        @Override
        protected File doInBackground(Metadata... metadata) {
            return artProvider.getArt(metadata[0]);
        }

        @Override
        protected void onPostExecute(File file) {
            if (file.exists()) {
                RequestCreator artRequest = Picasso
                        .with(NowPlayingActivity.this)
                        .load(file)
                        .placeholder(R.drawable.fallback_cover)
                        .error(R.drawable.fallback_cover);
                artRequest.into(artView);
                artRequest.into(artBackView);
            }
        }
    }
}
