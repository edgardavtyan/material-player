package com.edavtyan.materialplayer.views.lib.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.views.lib.adapters.RecyclerViewCursorAdapter;
import com.edavtyan.materialplayer.views.lib.decorators.DividerItemDecoration;
import com.edavtyan.materialplayer.utils.AppColors;

public abstract class ListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
	protected abstract Loader<Cursor> getLoader();
	protected abstract RecyclerViewCursorAdapter getAdapter();
	protected AppColors appColors;

	/*
	 * Fragment
	 */

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		appColors = new AppColors(getActivity());

		View view = inflater.inflate(R.layout.fragment_list, container, false);

		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setAdapter(getAdapter());
		recyclerView.addItemDecoration(new DividerItemDecoration(appColors.divider));

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getLoaderManager().initLoader(0, null, this);
	}

	/*
	 * LoaderCallbacks<Cursor>
	 */

	@Override
	public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
		return getLoader();
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		getAdapter().swapCursor(cursor);
	}


	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		getAdapter().swapCursor(null);
	}
}
