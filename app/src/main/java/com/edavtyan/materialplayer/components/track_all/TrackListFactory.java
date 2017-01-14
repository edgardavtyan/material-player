package com.edavtyan.materialplayer.components.track_all;

import android.content.Context;

import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.mvp.list.ListFactory;

public class TrackListFactory extends ListFactory {
	private final TrackListMvp.View view;
	private TrackListModel model;
	private TrackDB trackDB;
	private TrackListPresenter presenter;
	private TrackListAdapter adapter;

	public TrackListFactory(Context context, TrackListMvp.View view) {
		super(context);
		this.view = view;
	}

	public TrackListMvp.View getView() {
		return view;
	}

	public TrackListMvp.Model getModel() {
		if (model == null)
			model = new TrackListModel(getContext(), getTrackDB(), getCompactListPref());
		return model;
	}

	public TrackDB getTrackDB() {
		if (trackDB == null)
			trackDB = new TrackDB(getContext());
		return trackDB;
	}

	public TrackListMvp.Presenter getPresenter() {
		if (presenter == null)
			presenter = new TrackListPresenter(getView(), getModel());
		return presenter;
	}

	public TrackListAdapter getAdapter() {
		if (adapter == null)
			adapter = new TrackListAdapter(getContext(), getPresenter());
		return adapter;
	}
}
