package com.edavtyan.materialplayer.app.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.music.adapters.AlbumAdapter;
import com.edavtyan.materialplayer.app.vendor.DividerItemDecoration;

public class AlbumsListFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor> {
    /* ****** */
    /* Fields */
    /* ****** */

    private AlbumAdapter albumAdapter;

    /* **************** */
    /* Fragment members */
    /* **************** */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        albumAdapter = new AlbumAdapter(getContext(), null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_albums, container, false);

        RecyclerView albumsRecyclerView = (RecyclerView) view.findViewById(R.id.albums_list);
        albumsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        albumsRecyclerView.setAdapter(albumAdapter);
        albumsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), null));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, savedInstanceState, this);
    }

    /* *********************** */
    /* LoaderCallbacks members */
    /* *********************** */

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new AlbumLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        albumAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        albumAdapter.swapCursor(null);
    }

    private static class AlbumLoader extends CursorLoader {
        public AlbumLoader(Context context) {
            super(context);
        }

        @Override
        public Cursor loadInBackground() {
            return getContext().getContentResolver().query(
                    AlbumAdapter.URI,
                    AlbumAdapter.PROJECTION,
                    null, null,
                    AlbumAdapter.SORT_ORDER);
        }
    }
}
