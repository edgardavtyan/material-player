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
import com.edavtyan.materialplayer.app.music.adapters2.ArtistAdapter;

public class ArtistsListFragment extends Fragment {
    private ArtistAdapter artistAdapter;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Cursor cursor = getContext().getContentResolver().query(
                ArtistAdapter.URI,
                ArtistAdapter.PROJECTION,
                null, null,
                ArtistAdapter.SORT_ORDER);

        artistAdapter = new ArtistAdapter(getContext(), cursor);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artists, container, false);

        RecyclerView artistsRecyclerView = (RecyclerView) view.findViewById(R.id.artists_listview);
        artistsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        artistsRecyclerView.setAdapter(artistAdapter);

        return view;
    }
}
