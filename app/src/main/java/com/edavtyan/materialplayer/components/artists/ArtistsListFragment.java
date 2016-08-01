package com.edavtyan.materialplayer.components.artists;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import com.edavtyan.materialplayer.lib.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.lib.fragments.ListFragment;

public class ArtistsListFragment extends ListFragment {
	private ArtistsListAdapter artistsAdapter;
	private ArtistDB artistDB;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		artistDB = new ArtistDB(getContext());
		artistsAdapter = new ArtistsListAdapter(getContext(), artistDB);
	}

	@Override
	public Loader<Cursor> getLoader() {
		return artistDB.getAllArtistsLoader();
	}

	@Override
	public RecyclerViewCursorAdapter getAdapter() {
		return artistsAdapter;
	}
}
