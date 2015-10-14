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
import android.widget.ImageView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.music.adapters.TrackAdapter;
import com.edavtyan.materialplayer.app.music.adapters.TrackAdapterWithAlbumInfo;
import com.edavtyan.materialplayer.app.utils.AlbumArtUtils;

public class ArtistActivity
        extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {
    /* ********* */
    /* Constants */
    /* ********* */

    public static final String EXTRA_ARTIST_ID = "artist_id";
    public static final String EXTRA_ARTIST_NAME = "artist_name";
    private static final int LOADER_ID = 1;

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
        setContentView(R.layout.activity_artist);
        getLoaderManager().initLoader(LOADER_ID, null, this);
        trackAdapter = new TrackAdapterWithAlbumInfo(this, null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.artist_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.artist_collapsingToolbar);
        if (collapsingToolbar != null) {
            collapsingToolbar.setTitleEnabled(true);
            collapsingToolbar.setTitle(getIntent().getStringExtra(EXTRA_ARTIST_NAME));
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.artist_tracks_listview);
        recyclerView.setAdapter(trackAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageView artistArtView = (ImageView) findViewById(R.id.artist_art);
        AlbumArtUtils.getFullArtistArtRequest(this).into(artistArtView);
    }

    /* *********************** */
    /* LoaderCallbacks members */
    /* *********************** */

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new TracksLoader(this, getIntent().getIntExtra(EXTRA_ARTIST_ID, 0));
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
        private int artistId;

        public TracksLoader(Context context, int artistId) {
            super(context);
            this.artistId = artistId;
        }

        @Override
        public Cursor loadInBackground() {
            return getContext().getContentResolver().query(
                    TrackAdapter.URI,
                    TrackAdapter.PROJECTION,
                    TrackAdapter.COLUMN_NAME_ARTIST_ID + "=" + artistId,
                    null,
                    TrackAdapter.SORT_ORDER);
        }

    }
}
