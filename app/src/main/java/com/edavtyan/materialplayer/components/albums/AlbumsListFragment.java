package com.edavtyan.materialplayer.components.albums;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import com.edavtyan.materialplayer.lib.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.lib.fragments.ListFragment;

public class AlbumsListFragment extends ListFragment {
	private AlbumsListAdapter albumsAdapter;
	private AlbumDB albumDB;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		albumDB = new AlbumDB(getContext());
		albumsAdapter = new AlbumsListAdapter(getContext(), albumDB);
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
		return albumDB.getAllAlbumsLoader();
	}

	@Override
	public RecyclerViewCursorAdapter getAdapter() {
		return albumsAdapter;
	}
}
