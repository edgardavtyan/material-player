package com.edavtyan.materialplayer.screens.detail.artist_detail;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.screens.ActivityScope;
import com.edavtyan.materialplayer.screens.detail.lib.ParallaxHeaderListPresenter;
import com.edavtyan.materialplayer.screens.lists.lib.CompactListPref;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.utils.WebClient;

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
	@ActivityScope
	public AppCompatActivity provideActivity() {
		return activity;
	}

	@Provides
	@ActivityScope
	public ArtistDetailView provideView() {
		return view;
	}

	@Provides
	@ActivityScope
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
	@ActivityScope
	public ArtistDetailAdapter provideAdapter(AppCompatActivity activity, ArtistDetailPresenter presenter) {
		return new ArtistDetailAdapter(activity, presenter);
	}

	@Provides
	@ActivityScope
	public TestableRecyclerAdapter provideTestableRecyclerAdapter(ArtistDetailAdapter adapter) {
		return adapter;
	}

	@Provides
	@ActivityScope
	public ArtistDetailPresenter providePresenter(ArtistDetailModel model, ArtistDetailView view) {
		return new ArtistDetailPresenter(model, view);
	}

	@Provides
	@ActivityScope
	public ParallaxHeaderListPresenter provideParallaxPresenter(ArtistDetailPresenter presenter) {
		return presenter;
	}

	@Provides
	@ActivityScope
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
	@ActivityScope
	public ArtistDetailImageFileStorage provideImageFileStorage(Context context) {
		return new ArtistDetailImageFileStorage(context);
	}

	@Provides
	@ActivityScope
	public ArtistDetailImageMemoryCache provideImageMemoryCache() {
		return new ArtistDetailImageMemoryCache();
	}
}
