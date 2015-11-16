package com.edavtyan.materialplayer.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.base.BaseToolbarActivity;
import com.edavtyan.materialplayer.app.utils.DeviceUtils;

public class NowPlayingActivity extends BaseToolbarActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowplaying);
        initToolbar(R.string.nowplaying_toolbar_title);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (DeviceUtils.isPortrait(getResources())) {
            FloatingActionButton playlistFab = (FloatingActionButton) findViewById(R.id.fab_playlist);
            playlistFab.setOnClickListener(view -> openNowPlayingQueue());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        if (DeviceUtils.isLandscape(getResources())) {
            getMenuInflater().inflate(R.menu.menu_playlist, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_playlist:
                openNowPlayingQueue();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void openNowPlayingQueue() {
        startActivity(new Intent(this, PlaylistActivity.class));
    }
}
