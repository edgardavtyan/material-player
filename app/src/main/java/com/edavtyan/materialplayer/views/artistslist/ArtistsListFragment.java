package com.edavtyan.materialplayer.views.artistslist;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import com.edavtyan.materialplayer.views.lib.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.views.lib.fragments.ListFragment;
import com.edavtyan.materialplayer.models.artist.ArtistsProvider;

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
