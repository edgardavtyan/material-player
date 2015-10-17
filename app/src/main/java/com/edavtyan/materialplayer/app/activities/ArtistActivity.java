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
import com.edavtyan.materialplayer.app.music.adapters.TracksAdapter;
import com.edavtyan.materialplayer.app.music.adapters.ArtistTracksAdapter;
import com.edavtyan.materialplayer.app.utils.AlbumArtUtils;
import com.edavtyan.materialplayer.app.vendor.DividerItemDecoration;

public class ArtistActivity
        extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {
    /* ********* */
    /* Constants */
    /* ********* */

    public static final String EXTRA_ARTIST_ID = "artist_id";
    public static final String EXTRA_ARTIST_NAME = "artist_name";

    /* ****** */
    /* Fields */
    /* ****** */

    private TracksAdapter tracksAdapter;

    /* ************************* */
    /* AppCompatActivity members */
    /* ************************* */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        getLoaderManager().initLoader(0, null, this);
        tracksAdapter = new ArtistTracksAdapter(this, null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        if (collapsingToolbar != null) {
            collapsingToolbar.setTitleEnabled(true);
            collapsingToolbar.setTitle(getIntent().getStringExtra(EXTRA_ARTIST_NAME));
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.tracks_list);
        recyclerView.setAdapter(tracksAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, null));

        ImageView artistArtView = (ImageView) findViewById(R.id.art);
        AlbumArtUtils.getFullArtistArtRequest(this).into(artistArtView);
    }

    @Override
    protected void onDestroy() {
        tracksAdapter.closeConnection();
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

    /* *********************** */
    /* LoaderCallbacks members */
    /* *********************** */

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new TracksLoader(this, getIntent().getIntExtra(EXTRA_ARTIST_ID, 0));
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        tracksAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        tracksAdapter.swapCursor(null);
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
                    TracksAdapter.URI,
                    TracksAdapter.PROJECTION,
                    TracksAdapter.COLUMN_NAME_ARTIST_ID + "=" + artistId,
                    null,
                    TracksAdapter.SORT_ORDER);
        }

    }
}
