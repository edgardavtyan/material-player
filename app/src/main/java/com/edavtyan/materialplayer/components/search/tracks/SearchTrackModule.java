package com.edavtyan.materialplayer.components.search.tracks;

import android.content.Context;

import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.components.search.base.SearchViewImpl;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchTrackModule {
	private final SearchTrackFragment view;

	public SearchTrackModule(SearchTrackFragment view) {
		this.view = view;
	}

	@Provides
	@Singleton
	public SearchTrackFragment provideView() {
		return view;
	}

	@Provides
	@Singleton
	public SearchTrackAdapter provideAdapter(
			Context context, SearchTrackPresenter presenter) {
		return new SearchTrackAdapter(context, presenter);
	}

	@Provides
	@Singleton
	public SearchTrackModel provideModel(
			ModelServiceModule serviceModule,
			TrackDB trackDB,
			CompactListPref compactListPref) {
		return new SearchTrackModel(serviceModule, trackDB, compactListPref);
	}

	@Provides
	@Singleton
	public SearchTrackPresenter providePresenter(
			SearchTrackModel model, SearchTrackFragment view) {
		return new SearchTrackPresenter(model, view);
	}

	@Provides
	@Singleton
	public SearchViewImpl provideSearchViewImpl(
			SearchTrackFragment view, SearchTrackPresenter presenter) {
		return new SearchViewImpl(view, presenter);
	}
}
