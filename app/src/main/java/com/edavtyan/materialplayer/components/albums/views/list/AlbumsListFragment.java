package com.edavtyan.materialplayer.components.albums.views.list;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import com.edavtyan.materialplayer.lib.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.lib.fragments.ListFragment;
import com.edavtyan.materialplayer.components.albums.models.AlbumsProvider;

public class AlbumsListFragment extends ListFragment {
	private AlbumsListAdapter albumsAdapter;
	private AlbumsProvider albumsProvider;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		albumsAdapter = new AlbumsListAdapter(getContext(), null);
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
