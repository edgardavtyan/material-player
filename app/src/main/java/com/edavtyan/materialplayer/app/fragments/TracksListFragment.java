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
import com.edavtyan.materialplayer.app.music.adapters.TrackAdapter;
import com.edavtyan.materialplayer.app.music.adapters.TrackAdapterWithFullInfo;

public class TracksListFragment extends Fragment
implements LoaderManager.LoaderCallbacks<Cursor>{
    /* ****** */
    /* Fields */
    /* ****** */

    private TrackAdapter trackAdapter;

    /* **************** */
    /* Fragment members */
    /* **************** */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trackAdapter = new TrackAdapterWithFullInfo(getActivity(), null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracks, container, false);

        RecyclerView artistsRecyclerView = (RecyclerView) view.findViewById(R.id.track_list);
        artistsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        artistsRecyclerView.setAdapter(trackAdapter);

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
        return new TracksLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        trackAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        trackAdapter.swapCursor(null);
    }

    private static class TracksLoader extends CursorLoader {
        public TracksLoader(Context context) {
            super(context);
        }

        @Override
        public Cursor loadInBackground() {
            return getContext().getContentResolver().query(
                    TrackAdapter.URI,
                    TrackAdapter.PROJECTION,
                    null, null,
                    TrackAdapter.SORT_ORDER);
        }
    }
}
