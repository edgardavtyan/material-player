package com.edavtyan.materialplayer.app.views.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import com.edavtyan.materialplayer.app.adapters.AlbumsAdapter;
import com.edavtyan.materialplayer.app.lib.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.app.lib.fragments.ListFragment;
import com.edavtyan.materialplayer.app.models.providers.AlbumsProvider;

public class AlbumsListFragment extends ListFragment {
	private AlbumsAdapter albumsAdapter;
	private AlbumsProvider albumsProvider;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		albumsAdapter = new AlbumsAdapter(getContext(), null);
		albumsProvider = new AlbumsProvider(getContext());
	}

	@Override
	public void onResume() {
		super.onResume();
		albumsAdapter.bindService();
	}

	@Override
	public void onPause() {
		super.onPause();
		albumsAdapter.unbindService();
	}

	@Override
	public Loader<Cursor> getLoader() {
		return albumsProvider.getAllAlbumsLoader();
	}

	@Override
	public RecyclerViewCursorAdapter getAdapter() {
		return albumsAdapter;
	}
}
