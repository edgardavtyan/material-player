package com.edavtyan.materialplayer.components.detail.artist_detail;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.components.detail.lib.ParallaxHeaderListPresenter;
import com.edavtyan.materialplayer.components.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.utils.WebClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ArtistDetailFactory {
	private final AppCompatActivity activity;
	private final ArtistDetailView view;
	private final String artistTitle;

	public ArtistDetailFactory(AppCompatActivity activity, ArtistDetailView view, String artistTitle) {
		this.activity = activity;
		this.view = view;
		this.artistTitle = artistTitle;
	}

	@Provides
	@Singleton
	public Context provideContext() {
		return activity;
	}

	@Provides
	@Singleton
	public AppCompatActivity provideActivity() {
		return activity;
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
	public TestableRecyclerAdapter provideTestableRecyclerAdapter(ArtistDetailAdapter adapter) {
		return adapter;
	}

	@Provides
	@Singleton
	public ArtistDetailPresenter providePresenter(ArtistDetailModel model, ArtistDetailView view) {
		return new ArtistDetailPresenter(model, view);
	}

	@Provides
	@Singleton
	public ParallaxHeaderListPresenter provideParallaxPresenter(ArtistDetailPresenter presenter) {
		return presenter;
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
