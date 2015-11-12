package com.edavtyan.materialplayer.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.base.BaseToolbarActivity;

public class NowPlayingActivity extends BaseToolbarActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowplaying);
        initToolbar(R.string.nowplaying_toolbar_title);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton playlistFab = (FloatingActionButton) findViewById(R.id.fab_playlist);
        playlistFab.setOnClickListener(
                view -> startActivity(new Intent(this, PlaylistActivity.class)));
    }
}
