package com.edavtyan.materialplayer.components.detail.artist_detail;

import android.content.Context;

import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.db.AlbumDB;
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
public class ArtistDetailModule {
	private final Context context;
	private final ArtistDetailView view;
	private final String artistTitle;

	public ArtistDetailModule(Context context, ArtistDetailView view, String artistTitle) {
		this.context = context;
		this.view = view;
		this.artistTitle = artistTitle;
	}

	@Provides
	@Singleton
	public Context provideContext() {
		return context;
	}

	@Provides
	@Singleton
	public ArtistDetailView provideView() {
		return view;
	}

	@Provides
	@Singleton
	public ArtistDetailModel provideModel(
			ModelServiceModule serviceModule,
			ArtistDB artistDB,
			AlbumDB albumDB,
			TrackDB trackDB,
			CompactListPref compactListPref,
			ArtistDetailImageLoader imageLoader) {
		return new ArtistDetailModel(
				serviceModule, artistDB, albumDB, trackDB,
				compactListPref, imageLoader, artistTitle);
	}

	@Provides
	@Singleton
	public ArtistDetailAdapter provideAdapter(Context context, ArtistDetailPresenter presenter) {
		return new ArtistDetailAdapter(context, presenter);
	}

	@Provides
	@Singleton
	public ArtistDetailPresenter providePresenter(ArtistDetailModel model, ArtistDetailView view) {
		return new ArtistDetailPresenter(model, view);
	}

	@Provides
	@Singleton
	public ArtistDetailImageLoader provideImageLoader(
			WebClient webClient,
			LastfmApi lastFmApi,
			TestableBitmapFactory bitmapFactory,
			ArtistDetailImageFileStorage fileStorage,
			ArtistDetailImageMemoryCache memoryCache) {
		return new ArtistDetailImageLoader(
				webClient, lastFmApi, bitmapFactory, fileStorage, memoryCache);
	}

	@Provides
	@Singleton
	public ArtistDetailImageFileStorage provideImageFileStorage(Context context) {
		return new ArtistDetailImageFileStorage(context);
	}

	@Provides
	@Singleton
	public ArtistDetailImageMemoryCache provideImageMemoryCache() {
		return new ArtistDetailImageMemoryCache();
	}
}
