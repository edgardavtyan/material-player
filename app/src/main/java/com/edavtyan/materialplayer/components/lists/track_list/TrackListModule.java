package com.edavtyan.materialplayer.components.lists.track_list;

import android.content.Context;

import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TrackListModule {
	private final Context context;
	private final TrackListView view;

	public TrackListModule(Context context, TrackListView view) {
		this.context = context;
		this.view = view;
	}

	@Provides
	@Singleton
	public Context provideContext() {
		return context;
	}

	@Provides
	@Singleton
	public TrackListView provideView() {
		return view;
	}

	@Provides
	@Singleton
	public TrackListModel provideModel(
			ModelServiceModule serviceModule,
			TrackDB trackDB,
			CompactListPref compactListPref) {
		return new TrackListModel(serviceModule, trackDB, compactListPref);
	}

	@Provides
	@Singleton
	public TrackListPresenter providePresenter(TrackListView view, TrackListModel model) {
		return new TrackListPresenter(view, model);
	}

	@Provides
	@Singleton
	public TrackListAdapter provideAdapter(Context context, TrackListPresenter presenter) {
		return new TrackListAdapter(context, presenter);
	}
}
