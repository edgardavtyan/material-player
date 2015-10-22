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
import com.edavtyan.materialplayer.app.music.adapters.TracksAdapter;
import com.edavtyan.materialplayer.app.music.columns.TrackColumns;
import com.edavtyan.materialplayer.app.vendor.DividerItemDecoration;

public class TracksListFragment extends Fragment
implements LoaderManager.LoaderCallbacks<Cursor>{
    /* ****** */
    /* Fields */
    /* ****** */

    private TracksAdapter tracksAdapter;

    /* **************** */
    /* Fragment members */
    /* **************** */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tracksAdapter = new TracksAdapter(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracks, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.track_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(tracksAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), null));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onDestroy() {
        tracksAdapter.closeConnection();
        super.onDestroy();
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
        tracksAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        tracksAdapter.swapCursor(null);
    }

    private static class TracksLoader extends CursorLoader {
        public TracksLoader(Context context) {
            super(context);
        }

        @Override
        public Cursor loadInBackground() {
            return getContext().getContentResolver().query(
                    TrackColumns.URI,
                    TrackColumns.PROJECTION,
                    null, null,
                    TrackColumns.TITLE + " ASC");
        }
    }
}
