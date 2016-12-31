package com.edavtyan.materialplayer.components.track_all;

import android.content.Context;

import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class TrackListFactory extends BaseFactory {
	private final TrackListMvp.View view;
	private TrackListModel model;
	private TrackDB trackDB;
	private TrackListPresenter presenter;
	private TrackListAdapter adapter;

	public TrackListFactory(Context context, TrackListMvp.View view) {
		super(context);
		this.view = view;
	}

	public TrackListMvp.View provideView() {
		return view;
	}

	public TrackListMvp.Model provideModel() {
		if (model == null)
			model = new TrackListModel(provideContext(), provideTrackDB(), providePrefs());
		return model;
	}

	public TrackDB provideTrackDB() {
		if (trackDB == null)
			trackDB = new TrackDB(provideContext());
		return trackDB;
	}

	public TrackListMvp.Presenter providePresenter() {
		if (presenter == null)
			presenter = new TrackListPresenter(provideView(), provideModel());
		return presenter;
	}

	public TrackListAdapter provideAdapter() {
		if (adapter == null)
			adapter = new TrackListAdapter(provideContext(), providePresenter());
		return adapter;
	}
}
