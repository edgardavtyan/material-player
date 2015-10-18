package com.edavtyan.materialplayer.app.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.music.adapters.ArtistsAdapter;

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
                ArtistsAdapter.URI,
                ArtistsAdapter.PROJECTION,
                null, null,
                ArtistsAdapter.SORT_ORDER);
    }

    @Override
    public RecyclerViewCursorAdapter getAdapter() {
        return artistsAdapter;
    }
}
