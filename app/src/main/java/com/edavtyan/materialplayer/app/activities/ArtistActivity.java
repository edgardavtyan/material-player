package com.edavtyan.materialplayer.app.activities;

import android.app.LoaderManager;
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
import com.edavtyan.materialplayer.app.music.adapters.AlbumsAdapter;
import com.edavtyan.materialplayer.app.utils.AlbumArtUtils;
import com.edavtyan.materialplayer.app.vendor.DividerItemDecoration;

public class ArtistActivity
        extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {
    /* ********* */
    /* Constants */
    /* ********* */

    public static final String EXTRA_ARTIST_NAME = "artist_name";

    /* ****** */
    /* Fields */
    /* ****** */

    private AlbumsAdapter tracksAdapter;

    /* ************************* */
    /* AppCompatActivity members */
    /* ************************* */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_list);
        getLoaderManager().initLoader(0, null, this);
        tracksAdapter = new AlbumsAdapter(this, null);

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
        String artist = getIntent().getStringExtra(EXTRA_ARTIST_NAME);
        return new CursorLoader(
                this,
                AlbumsAdapter.URI,
                AlbumsAdapter.PROJECTION,
                AlbumsAdapter.COLUMN_ARTIST + "='" + artist + "'",
                null,
                AlbumsAdapter.SORT_ORDER);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        tracksAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        tracksAdapter.swapCursor(null);
    }
}
