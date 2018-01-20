package com.edavtyan.materialplayer.screens.lists.track_list;

import android.support.v4.app.FragmentActivity;

import com.edavtyan.materialplayer.screens.FragmentScope;
import com.edavtyan.materialplayer.screens.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import dagger.Module;
import dagger.Provides;

@Module
public class TrackListModule {
	private final FragmentActivity activity;
	private final TrackListView view;

	public TrackListModule(FragmentActivity activity, TrackListView view) {
		this.activity = activity;
		this.view = view;
	}

	@Provides
	@FragmentScope
	public FragmentActivity provideActivity() {
		return activity;
	}

	@Provides
	@FragmentScope
	public TrackListView provideView() {
		return view;
	}

	@Provides
	@FragmentScope
	public TrackListModel provideModel(
			ModelServiceModule serviceModule,
			TrackDB trackDB,
			CompactListPref compactListPref) {
		return new TrackListModel(serviceModule, trackDB, compactListPref);
	}

	@Provides
	@FragmentScope
	public TrackListPresenter providePresenter(TrackListView view, TrackListModel model) {
		return new TrackListPresenter(view, model);
	}

	@Provides
	@FragmentScope
	public TrackListAdapter provideAdapter(FragmentActivity activity, TrackListPresenter presenter) {
		return new TrackListAdapter(activity, presenter);
	}
}
