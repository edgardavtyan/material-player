package com.edavtyan.materialplayer.app.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.edavtyan.materialplayer.app.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.fragments.base.ListFragment;
import com.edavtyan.materialplayer.app.music.adapters.AlbumsAdapter;
import com.edavtyan.materialplayer.app.music.columns.AlbumColumns;

public class AlbumsListFragment extends ListFragment {
    private AlbumsAdapter albumsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        albumsAdapter = new AlbumsAdapter(getContext(), null);
    }

    @Override
    public void onResume() {
        super.onResume();
        albumsAdapter.bindService();
    }

    @Override
    public void onPause() {
        super.onPause();
        albumsAdapter.unbindService();
    }

    @Override
    public Loader<Cursor> getLoader() {
        return new CursorLoader(
                getContext(),
                AlbumColumns.URI,
                AlbumColumns.PROJECTION,
                null, null,
                AlbumColumns.NAME_TITLE + " ASC");
    }

    @Override
    public RecyclerViewCursorAdapter getAdapter() {
        return albumsAdapter;
    }
}
