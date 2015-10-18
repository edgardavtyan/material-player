package com.edavtyan.materialplayer.app.activities;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.music.ArtProvider;
import com.edavtyan.materialplayer.app.music.Metadata;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.services.MusicPlayerService.MusicPlayerBinder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

public class NowPlayingActivity
        extends AppCompatActivity
        implements ServiceConnection {
    /* ****** */
    /* Fields */
    /* ****** */

    private ArtProvider artProvider;

    private MusicPlayerService playerService;
    private boolean isBound;

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

        artView = (ImageView) findViewById(R.id.art);
        artBackView = (ImageView) findViewById(R.id.art_back);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            FrameLayout artFrame = (FrameLayout) findViewById(R.id.art_frame);
            artFrame.getLayoutParams().height = getResources().getDisplayMetrics().widthPixels;
        }

        Intent intent = new Intent(this, MusicPlayerService.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);

        registerReceiver(newTrackReceiver, new IntentFilter(MusicPlayerService.SEND_NEW_TRACK));
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = new Intent(this, MusicPlayerService.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
        registerReceiver(newTrackReceiver, new IntentFilter(MusicPlayerService.SEND_NEW_TRACK));

        if (isBound) {
            syncTrackInfoWithService();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
        unregisterReceiver(newTrackReceiver);
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

    /*
     * ServiceConnection members
     */

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

    /* *************** */
    /* Private methods */
    /* *************** */

    private void syncTrackInfoWithService() {
        new ArtLoadTask().execute(playerService.getMetadata());
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
