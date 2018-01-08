package com.edavtyan.materialplayer.components.search.artist;

import android.content.Context;

import com.edavtyan.materialplayer.components.lists.artist_list.ArtistListImageLoader;
import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.components.search.base.SearchViewImpl;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchArtistModule {
	private final SearchArtistFragment view;

	public SearchArtistModule(SearchArtistFragment view) {
		this.view = view;
	}

	@Provides
	@Singleton
	public SearchArtistFragment provideView() {
		return view;
	}

	@Provides
	@Singleton
	public SearchArtistAdapter provideAdapter(
			Context context, SearchArtistPresenter presenter) {
		return new SearchArtistAdapter(context, presenter);
	}

	@Provides
	@Singleton
	public SearchArtistModel provideModel(
			ModelServiceModule serviceModule,
			ArtistDB artistDB,
			TrackDB trackDB,
			ArtistListImageLoader imageLoader,
			CompactListPref compactListPref) {
		return new SearchArtistModel(
				serviceModule, artistDB, trackDB, imageLoader, compactListPref);
	}

	@Provides
	@Singleton
	public SearchArtistPresenter providePresenter(
			SearchArtistModel model, SearchArtistFragment view) {
		return new SearchArtistPresenter(model, view);
	}

	@Provides
	@Singleton
	public SearchViewImpl provideSearchViewImpl(
			SearchArtistFragment view, SearchArtistPresenter presenter) {
		return new SearchViewImpl(view, presenter);
	}
}
