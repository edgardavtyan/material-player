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
import com.edavtyan.materialplayer.app.music.adapters.ArtistsAdapter;
import com.edavtyan.materialplayer.app.vendor.DividerItemDecoration;

public class ArtistsListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    /* ****** */
    /* Fields */
    /* ****** */

    private ArtistsAdapter artistsAdapter;


    /* **************** */
    /* Fragment members */
    /* **************** */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        artistsAdapter = new ArtistsAdapter(getContext(), null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artists, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.artists_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(artistsAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), null));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    /* *********************** */
    /* LoaderCallbacks members */
    /* *********************** */

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new ArtistsLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        artistsAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        artistsAdapter.swapCursor(null);
    }

    private static class ArtistsLoader extends CursorLoader {
        public ArtistsLoader(Context context) {
            super(context);
        }

        @Override
        public Cursor loadInBackground() {
            return getContext().getContentResolver().query(
                    ArtistsAdapter.URI,
                    ArtistsAdapter.PROJECTION,
                    null, null,
                    ArtistsAdapter.SORT_ORDER);
        }
    }
}
