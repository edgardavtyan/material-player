package com.edavtyan.materialplayer.components.track_all;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.mvp.list.ListAdapter;

public class TrackListAdapter extends ListAdapter<TrackListViewHolder> {

	private final TrackListMvp.Presenter presenter;

	public TrackListAdapter(Context context, TrackListMvp.Presenter presenter) {
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
