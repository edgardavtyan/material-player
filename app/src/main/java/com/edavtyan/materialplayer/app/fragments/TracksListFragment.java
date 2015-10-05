package com.edavtyan.materialplayer.app.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.music.adapters2.TrackAdapter;

public class TracksListFragment extends Fragment {
    private TrackAdapter trackAdapter;

    private Cursor cursor;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cursor = getContext().getContentResolver().query(
                TrackAdapter.URI, TrackAdapter.PROJECTION,
                null, null, TrackAdapter.SORT_ORDER);

        trackAdapter = new TrackAdapter(getActivity(), cursor);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracks, container, false);

        RecyclerView artistsRecyclerView = (RecyclerView) view.findViewById(R.id.track_listview);
        artistsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        artistsRecyclerView.setAdapter(trackAdapter);

        return view;
    }
}
