package com.edavtyan.materialplayer.components.lists.artist_list;

import android.content.Context;

import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.utils.WebClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ArtistListModule {
	private final Context context;
	private final ArtistListView view;

	public ArtistListModule(Context context, ArtistListView view) {
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
	public ArtistListView provideView() {
		return view;
	}

	@Provides
	@Singleton
	public ArtistListModel provideModel(
			ModelServiceModule serviceModule,
			ArtistDB artistDB,
			TrackDB trackDB,
			ArtistListImageLoader imageLoader,
			CompactListPref compactListPref) {
		return new ArtistListModel(
				serviceModule,
				artistDB,
				trackDB,
				imageLoader,
				compactListPref);
	}

	@Provides
	@Singleton
	public ArtistListPresenter providePresenter(ArtistListModel model, ArtistListView view) {
		return new ArtistListPresenter(model, view);
	}

	@Provides
	@Singleton
	public ArtistListAdapter provideAdapter(Context context, ArtistListPresenter presenter) {
		return new ArtistListAdapter(context, presenter);
	}

	@Provides
	@Singleton
	public ArtistListImageLoader provideImageLoader(
			WebClient webClient,
			TestableBitmapFactory bitmapFactory,
			LastfmApi lastfmApi,
			ArtistListImageFileStorage fileStorage,
			ArtistListImageMemoryCache memoryCache) {
		return new ArtistListImageLoader(webClient, bitmapFactory, lastfmApi, fileStorage, memoryCache);
	}

	@Provides
	@Singleton
	public ArtistListImageFileStorage provideImageFileStorage(Context context) {
		return new ArtistListImageFileStorage(context);
	}

	@Provides
	@Singleton
	public ArtistListImageMemoryCache provideImageMemoryCache() {
		return new ArtistListImageMemoryCache();
	}
}
