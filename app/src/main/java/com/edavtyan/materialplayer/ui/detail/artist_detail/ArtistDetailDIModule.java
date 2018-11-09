package com.edavtyan.materialplayer.ui.detail.artist_detail;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.lib.testable.TestableRecyclerAdapter;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.ActivityScope;
import com.edavtyan.materialplayer.ui.SdkFactory;
import com.edavtyan.materialplayer.ui.detail.lib.ParallaxHeaderListPresenter;
import com.edavtyan.materialplayer.utils.WebClient;

import dagger.Module;
import dagger.Provides;

@Module
public class ArtistDetailDIModule {
	private final ArtistDetailActivity activity;
	private final String artistTitle;

	public ArtistDetailDIModule(ArtistDetailActivity activity, String artistTitle) {
		this.activity = activity;
		this.artistTitle = artistTitle;
	}

	@Provides
	@ActivityScope
	public AppCompatActivity provideActivity() {
		return activity;
	}

	@Provides
	@ActivityScope
	public ArtistDetailActivity provideArtistDetailActivity() {
		return activity;
	}

	@Provides
	@ActivityScope
	public ArtistDetailModel provideModel(
			ModelServiceModule serviceModule,
			ArtistDB artistDB,
			AlbumDB albumDB,
			TrackDB trackDB,
			ArtistDetailImageLoader imageLoader) {
		return new ArtistDetailModel(
				serviceModule, artistDB, albumDB, trackDB, imageLoader, artistTitle);
	}

	@Provides
	@ActivityScope
	public ArtistDetailAdapter provideAdapter(
			AppCompatActivity activity, ArtistDetailPresenter presenter, SdkFactory sdkFactory) {
		return new ArtistDetailAdapter(activity, presenter, sdkFactory);
	}

	@Provides
	@ActivityScope
	public TestableRecyclerAdapter provideTestableRecyclerAdapter(ArtistDetailAdapter adapter) {
		return adapter;
	}

	@Provides
	@ActivityScope
	public ArtistDetailPresenter providePresenter(
			ArtistDetailModel model, ArtistDetailActivity activity, ThemeColors theme) {
		return new ArtistDetailPresenter(model, activity, theme);
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
