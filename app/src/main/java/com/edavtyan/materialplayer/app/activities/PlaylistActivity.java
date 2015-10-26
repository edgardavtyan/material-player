package com.edavtyan.materialplayer.app.activities;

import android.content.ComponentName;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.base.ServiceActivity;
import com.edavtyan.materialplayer.app.music.adapters.PlaylistAdapter;
import com.edavtyan.materialplayer.app.music.columns.TrackColumns;

import java.util.Arrays;

public class PlaylistActivity
        extends ServiceActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {
    private PlaylistAdapter playlistAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        playlistAdapter = new PlaylistAdapter(this);

        RecyclerView playlistView = (RecyclerView) findViewById(R.id.list);
        playlistView.setLayoutManager(new LinearLayoutManager(this));
        playlistView.setAdapter(playlistAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        super.onServiceConnected(componentName, iBinder);
        getSupportLoaderManager().initLoader(0, null, this);
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
     * LoaderCallbacks
     */

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String trackIds = Arrays.toString(getService().getTracks().toArray());
        trackIds = trackIds.substring(1, trackIds.length() - 1);

        return new CursorLoader(
                this,
                TrackColumns.URI,
                TrackColumns.PROJECTION,
                TrackColumns.NAME_ID + " in (" + trackIds + ")",
                null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        playlistAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        playlistAdapter.swapCursor(null);
    }
}
