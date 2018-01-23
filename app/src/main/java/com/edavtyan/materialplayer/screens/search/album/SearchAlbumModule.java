package com.edavtyan.materialplayer.screens.search.album;

import android.support.v4.app.FragmentActivity;

import com.edavtyan.materialplayer.screens.FragmentScope;
import com.edavtyan.materialplayer.screens.SdkFactory;
import com.edavtyan.materialplayer.screens.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.screens.search.base.SearchViewImpl;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchAlbumModule {
	private final SearchAlbumFragment view;

	public SearchAlbumModule(SearchAlbumFragment view) {
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
			FragmentActivity activity, SearchAlbumPresenter presenter, SdkFactory sdkFactory) {
		return new SearchAlbumAdapter(activity, presenter, sdkFactory);
	}

	@Provides
	@FragmentScope
	public SearchAlbumModel provideModel(
			ModelServiceModule serviceModule,
			AlbumDB albumDB,
			TrackDB trackDB,
			CompactListPref compactListPref) {
		return new SearchAlbumModel(serviceModule, albumDB, trackDB, compactListPref);
	}

	@Provides
	@FragmentScope
	public SearchAlbumPresenter providePresenter(
			SearchAlbumModel model, SearchAlbumFragment view) {
		return new SearchAlbumPresenter(model, view);
	}

	@Provides
	@FragmentScope
	public SearchViewImpl provideSearchViewImpl(
			SearchAlbumFragment view, SearchAlbumPresenter presenter) {
		return new SearchViewImpl(view, presenter);
	}
}
