package com.edavtyan.materialplayer.app.activities;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.music.adapters.TrackAdapter;
import com.edavtyan.materialplayer.app.music.adapters.TrackAdapterWithDurationInfo;
import com.edavtyan.materialplayer.app.utils.AlbumArtUtils;
import com.edavtyan.materialplayer.app.vendor.DividerItemDecoration;

public class AlbumActivity
        extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {
    /* ********* */
    /* Constants */
    /* ********* */
    public static final String EXTRA_ALBUM_TITLE = "album_title";
    public static final String EXTRA_ALBUM_ART = "album_art";
    public static final String EXTRA_ALBUM_ID = "album_id";

    /* ****** */
    /* Fields */
    /* ****** */

    private TrackAdapter trackAdapter;

    /* ************************* */
    /* AppCompatActivity members */
    /* ************************* */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        getLoaderManager().initLoader(0, savedInstanceState, this);
        trackAdapter = new TrackAdapterWithDurationInfo(this, null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        if (collapsingToolbar != null) {
            collapsingToolbar.setTitleEnabled(true);
            collapsingToolbar.setTitle(getIntent().getStringExtra(EXTRA_ALBUM_TITLE));
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.tracks_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(trackAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, null));

        ImageView artView = (ImageView) findViewById(R.id.art);
        String artPath = getIntent().getStringExtra(EXTRA_ALBUM_ART);
        AlbumArtUtils
                .getFullArtRequest(this, AlbumArtUtils.getArtFileFromPath(artPath))
                .into(artView);
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

    /* *********************** */
    /* LoaderCallbacks members */
    /* *********************** */

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new TracksLoader(this, getIntent().getIntExtra(EXTRA_ALBUM_ID, 0));
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        trackAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        trackAdapter.swapCursor(null);
    }

    private static class TracksLoader extends CursorLoader {
        private final int albumId;

        public TracksLoader(Context context, int albumId) {
            super(context);
            this.albumId = albumId;
        }

        @Override
        public Cursor loadInBackground() {
            return getContext().getContentResolver().query(
                    TrackAdapter.URI,
                    TrackAdapter.PROJECTION,
                    TrackAdapter.COLUMN_NAME_ALBUM_ID + "=" + albumId,
                    null,
                    TrackAdapter.SORT_ORDER);
        }
    }
}
