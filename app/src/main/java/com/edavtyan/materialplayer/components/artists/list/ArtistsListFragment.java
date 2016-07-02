package com.edavtyan.materialplayer.components.artists.list;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import com.edavtyan.materialplayer.lib.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.lib.fragments.ListFragment;
import com.edavtyan.materialplayer.components.artists.ArtistsProvider;

public class ArtistsListFragment extends ListFragment {
	private ArtistsListAdapter artistsAdapter;
	private ArtistsProvider artistsProvider;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		artistsAdapter = new ArtistsListAdapter(getContext(), null);
		artistsProvider = new ArtistsProvider(getContext());
	}

	@Override
	public Loader<Cursor> getLoader() {
		return artistsProvider.getAllArtistsLoader();
	}

	@Override
	public RecyclerViewCursorAdapter getAdapter() {
		return artistsAdapter;
	}
}
