package com.edavtyan.materialplayer.app.views.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.edavtyan.materialplayer.app.lib.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.lib.fragments.ListFragment;
import com.edavtyan.materialplayer.app.adapters.ArtistsAdapter;
import com.edavtyan.materialplayer.app.models.columns.ArtistColumns;

public class ArtistsListFragment extends ListFragment {
	private ArtistsAdapter artistsAdapter;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		artistsAdapter = new ArtistsAdapter(getContext(), null);
	}

	@Override
	public Loader<Cursor> getLoader() {
		return new CursorLoader(
				getContext(),
				ArtistColumns.URI,
				ArtistColumns.PROJECTION,
				null, null,
				ArtistColumns.NAME_TITLE + " ASC");
	}

	@Override
	public RecyclerViewCursorAdapter getAdapter() {
		return artistsAdapter;
	}
}
