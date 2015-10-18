package com.edavtyan.materialplayer.app.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.widget.ImageView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.base.CollapsingHeaderListActivity;
import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.music.adapters.AlbumsAdapter;
import com.squareup.picasso.Picasso;

public class ArtistActivity extends CollapsingHeaderListActivity {

    public static final String EXTRA_ARTIST_NAME = "artist_name";

    private AlbumsAdapter albumsAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        albumsAdapter = new AlbumsAdapter(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public Loader<Cursor> getLoader() {
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
    public RecyclerViewCursorAdapter getAdapter() {
        return albumsAdapter;
    }

    @Override
    public String getToolbarTitle() {
        return getIntent().getStringExtra(EXTRA_ARTIST_NAME);
    }

    @Override
    public void setImageSource(ImageView imageView) {
        Picasso.with(this)
                .load(R.drawable.fallback_artist)
                .into(imageView);
    }
}
