package com.edavtyan.materialplayer.app.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.fragments.base.ListFragment;
import com.edavtyan.materialplayer.app.music.adapters.AlbumsAdapter;

public class AlbumsListFragment extends ListFragment {
    private AlbumsAdapter albumsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        albumsAdapter = new AlbumsAdapter(getContext(), null);
    }

    @Override
    public Loader<Cursor> getLoader() {
        return new CursorLoader(
                getContext(),
                AlbumsAdapter.URI,
                AlbumsAdapter.PROJECTION,
                null, null,
                AlbumsAdapter.SORT_ORDER);
    }

    @Override
    public RecyclerViewCursorAdapter getAdapter() {
        return albumsAdapter;
    }
}
