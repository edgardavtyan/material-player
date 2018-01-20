package com.edavtyan.materialplayer.screens.lists.track_list;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.screens.lists.lib.ListAdapter;

public class TrackListAdapter extends ListAdapter<TrackListViewHolder> {

	private final TrackListPresenter presenter;

	public TrackListAdapter(Context context, TrackListPresenter presenter) {
		super(context, presenter);
		this.presenter = presenter;
	}

	@Override
	public int getNormalLayoutId() {
		return R.layout.listitem_track;
	}

	@Override
	public int getCompactLayoutId() {
		return R.layout.listitem_track_compact;
	}

	@Override
	public TrackListViewHolder onCreateViewHolder(Context context, View view) {
		return new TrackListViewHolder(context, view, presenter);
	}
}
