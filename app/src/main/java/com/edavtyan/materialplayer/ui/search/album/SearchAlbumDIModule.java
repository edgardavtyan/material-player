package com.edavtyan.materialplayer.ui.search.album;

import android.app.Activity;

import com.edavtyan.materialplayer.db.MediaDB;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.ui.SdkFactory;
import com.edavtyan.materialplayer.ui.search.base.SearchViewImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchAlbumDIModule {
	private final SearchAlbumFragment view;

	public SearchAlbumDIModule(SearchAlbumFragment view) {
		this.view = view;
	}

	@Provides
	@FragmentScope
	public SearchAlbumFragment provideView() {
		return view;
	}

	@Provides
	@FragmentScope
	public SearchAlbumAdapter provideAdapter(
			Activity activity, SearchAlbumPresenter presenter, SdkFactory sdkFactory) {
		return new SearchAlbumAdapter(activity, presenter, sdkFactory);
	}

	@Provides
	@FragmentScope
	public SearchAlbumModel provideModel(
			ModelServiceModule serviceModule,
			MediaDB mediaDB,
			AlbumArtProvider albumArtProvider) {
		return new SearchAlbumModel(serviceModule, mediaDB, albumArtProvider);
	}

	@Provides
	@FragmentScope
	public SearchAlbumPresenter providePresenter(SearchAlbumModel model, SearchAlbumFragment view) {
		return new SearchAlbumPresenter(model, view);
	}

	@Provides
	@FragmentScope
	public SearchViewImpl provideSearchViewImpl(
			SearchAlbumFragment view, SearchAlbumPresenter presenter) {
		return new SearchViewImpl(view, presenter);
	}
}
