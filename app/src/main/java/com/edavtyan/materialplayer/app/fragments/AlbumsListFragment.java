package com.edavtyan.materialplayer.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.music.adapters.AlbumAdapter;
import com.edavtyan.materialplayer.app.music.providers.AlbumsProvider;

public class AlbumsListFragment extends Fragment {
    private AlbumsProvider albumsProvider;
    private AlbumAdapter albumAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        albumsProvider = new AlbumsProvider(getActivity());
        albumAdapter = new AlbumAdapter(albumsProvider.getAlbums());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_albums, container, false);

        RecyclerView albumsRecyclerView = (RecyclerView) view.findViewById(R.id.albums_listview);
        albumsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        albumsRecyclerView.setAdapter(albumAdapter);

        return view;
    }
}
