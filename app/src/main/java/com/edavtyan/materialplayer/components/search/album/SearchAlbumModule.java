package com.edavtyan.materialplayer.components.search.album;

import android.content.Context;

import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.components.search.base.SearchViewImpl;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchAlbumModule {
	private final SearchAlbumFragment view;

	public SearchAlbumModule(SearchAlbumFragment view) {
		this.view = view;
	}

	@Provides
	@Singleton
	public SearchAlbumFragment provideView() {
		return view;
	}

	@Provides
	@Singleton
	public SearchAlbumAdapter provideAdapter(
			Context context, SearchAlbumPresenter presenter) {
		return new SearchAlbumAdapter(context, presenter);
	}

	@Provides
	@Singleton
	public SearchAlbumModel provideModel(
			ModelServiceModule serviceModule,
			AlbumDB albumDB,
			TrackDB trackDB,
			CompactListPref compactListPref) {
		return new SearchAlbumModel(serviceModule, albumDB, trackDB, compactListPref);
	}

	@Provides
	@Singleton
	public SearchAlbumPresenter providePresenter(
			SearchAlbumModel model, SearchAlbumFragment view) {
		return new SearchAlbumPresenter(model, view);
	}

	@Provides
	@Singleton
	public SearchViewImpl provideSearchViewImpl(
			SearchAlbumFragment view, SearchAlbumPresenter presenter) {
		return new SearchViewImpl(view, presenter);
	}
}
