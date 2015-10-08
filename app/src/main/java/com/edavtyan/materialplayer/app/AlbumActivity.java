package com.edavtyan.materialplayer.app;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import com.edavtyan.materialplayer.app.music.adapters.TrackAdapter;
import com.squareup.picasso.Picasso;

import java.io.File;

public class AlbumActivity
        extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String EXTRA_ALBUM_TITLE = "album_title";
    public static final String EXTRA_ALBUM_ART = "album_art";
    public static final String EXTRA_ALBUM_ID = "album_id";

    public static final int LOADER_ID = 2;



    private TrackAdapter trackAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        getLoaderManager().initLoader(LOADER_ID, savedInstanceState, this);
        trackAdapter = new TrackAdapter(this, null, TrackAdapter.TrackInfoAmount.TIME_ONLY);

        initRecyclerView();
        initToolbar();
        initCollapsingToolbar();
    }



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


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.album_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initCollapsingToolbar() {
        CollapsingToolbarLayout toolbar =
                (CollapsingToolbarLayout) findViewById(R.id.album_collapsingToolbar);
        toolbar.setTitleEnabled(true);
        toolbar.setTitle(getIntent().getStringExtra(EXTRA_ALBUM_TITLE));

        ImageView artView = (ImageView) findViewById(R.id.album_art);
        Picasso.with(this)
                .load(new File(getIntent().getStringExtra(EXTRA_ALBUM_ART)))
                .into(artView);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.album_tracks_listview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(trackAdapter);
    }
}
