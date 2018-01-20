package com.edavtyan.materialplayer.components.search.tracks;

import android.support.v4.app.FragmentActivity;

import com.edavtyan.materialplayer.components.FragmentScope;
import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.components.search.base.SearchViewImpl;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchTrackModule {
	private final SearchTrackFragment view;

	public SearchTrackModule(SearchTrackFragment view) {
		this.view = view;
	}

	@Provides
	@FragmentScope
	public SearchTrackFragment provideView() {
		return view;
	}

	@Provides
	@FragmentScope
	public SearchTrackAdapter provideAdapter(
			FragmentActivity activity, SearchTrackPresenter presenter) {
		return new SearchTrackAdapter(activity, presenter);
	}

	@Provides
	@FragmentScope
	public SearchTrackModel provideModel(
			ModelServiceModule serviceModule,
			TrackDB trackDB,
			CompactListPref compactListPref) {
		return new SearchTrackModel(serviceModule, trackDB, compactListPref);
	}

	@Provides
	@FragmentScope
	public SearchTrackPresenter providePresenter(
			SearchTrackModel model, SearchTrackFragment view) {
		return new SearchTrackPresenter(model, view);
	}

	@Provides
	@FragmentScope
	public SearchViewImpl provideSearchViewImpl(
			SearchTrackFragment view, SearchTrackPresenter presenter) {
		return new SearchViewImpl(view, presenter);
	}
}
