package com.edavtyan.materialplayer.components.lists.track_list;

import android.content.Context;

import com.edavtyan.materialplayer.db.DBModule;
import com.edavtyan.materialplayer.components.lists.lib.ListFactory;

public class TrackListFactory extends ListFactory {
	private final TrackListMvp.View view;
	private final DBModule dbModule;
	private TrackListModel model;
	private TrackListPresenter presenter;
	private TrackListAdapter adapter;

	public TrackListFactory(Context context, TrackListMvp.View view) {
		super(context);
		this.view = view;
		dbModule = new DBModule(context);
	}

	public TrackListMvp.View getView() {
		return view;
	}

	public TrackListMvp.Model getModel() {
		if (model == null)
			model = new TrackListModel(getModelServiceModule(), dbModule.getTrackDB(), getCompactListPref());
		return model;
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
