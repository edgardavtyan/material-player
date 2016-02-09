package com.edavtyan.materialplayer.app.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.fragments.base.ListFragment;
import com.edavtyan.materialplayer.app.music.adapters.ArtistsAdapter;
import com.edavtyan.materialplayer.app.music.columns.ArtistColumns;

public class ArtistsListFragment extends ListFragment {
    private ArtistsAdapter artistsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        artistsAdapter = new ArtistsAdapter(getContext(), null);
    }

    @Override
    public Loader<Cursor> getLoader() {
        return new CursorLoader(
                getContext(),
                ArtistColumns.URI,
                ArtistColumns.PROJECTION,
                null, null,
                ArtistColumns.NAME_TITLE + " ASC");
    }

    @Override
    public RecyclerViewCursorAdapter getAdapter() {
        return artistsAdapter;
    }
}
