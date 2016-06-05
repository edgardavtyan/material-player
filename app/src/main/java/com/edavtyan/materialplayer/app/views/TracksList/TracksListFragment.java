package com.edavtyan.materialplayer.app.views.trackslist;

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

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.models.track.TracksProvider;
import com.edavtyan.materialplayer.app.utils.AppColors;
import com.edavtyan.materialplayer.app.views.lib.decorators.DividerItemDecoration;

public class TracksListFragment extends Fragment
		implements LoaderManager.LoaderCallbacks<Cursor> {
	private TracksListAdapter tracksAdapter;
	private TracksProvider tracksProvider;
	private AppColors appColors;

	/*
	 * Fragment
	 */

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tracksAdapter = new TracksListAdapter(getActivity(), null);
		tracksProvider = new TracksProvider(getActivity());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater,
	                         @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		appColors = new AppColors(getActivity());

		View view = inflater.inflate(R.layout.fragment_list, container, false);

		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setAdapter(tracksAdapter);
		recyclerView.addItemDecoration(new DividerItemDecoration(appColors.divider));

		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public void onResume() {
		super.onResume();
		tracksAdapter.bindService();
	}

	@Override
	public void onPause() {
		super.onPause();
		tracksAdapter.unbindService();
	}

	/*
	 * LoaderCallbacks
	 */

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return tracksProvider.getAllTracksLoader();
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		tracksAdapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		tracksAdapter.swapCursor(null);
	}
}
