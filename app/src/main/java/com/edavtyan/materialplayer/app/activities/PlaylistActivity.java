package com.edavtyan.materialplayer.app.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.base.BaseToolbarActivity;
import com.edavtyan.materialplayer.app.music.adapters.PlaylistAdapter;
import com.edavtyan.materialplayer.app.decorators.DividerItemDecoration;
import com.edavtyan.materialplayer.app.resources.AppColors;

public class PlaylistActivity extends BaseToolbarActivity {
    private PlaylistAdapter playlistAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        initToolbar(R.string.queue_title);

        playlistAdapter = new PlaylistAdapter(this);

        RecyclerView playlistView = (RecyclerView) findViewById(R.id.list);
        playlistView.setLayoutManager(new LinearLayoutManager(this));
        playlistView.setAdapter(playlistAdapter);
        playlistView.addItemDecoration(new DividerItemDecoration(AppColors.getDivider(this)));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    public void onResume() {
        super.onResume();
        playlistAdapter.registerReceivers();
        playlistAdapter.bindService();
    }

    @Override
    public void onPause() {
        super.onPause();
        playlistAdapter.unbindService();
        playlistAdapter.unregisterReceivers();
    }
}
