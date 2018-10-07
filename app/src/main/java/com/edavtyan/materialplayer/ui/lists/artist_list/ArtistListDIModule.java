package com.edavtyan.materialplayer.ui.lists.artist_list;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.ui.SdkFactory;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.testable.TestableBitmapFactory;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.utils.WebClient;

import dagger.Module;
import dagger.Provides;

@Module
public class ArtistListDIModule {
	private final FragmentActivity activity;
	private final ArtistListFragment view;

	public ArtistListDIModule(FragmentActivity activity, ArtistListFragment view) {
		this.activity = activity;
		this.view = view;
	}

	@Provides
	@FragmentScope
	public FragmentActivity provideActivity() {
		return activity;
	}

	@Provides
	@FragmentScope
	public ArtistListView provideView() {
		return view;
	}

	@Provides
	@FragmentScope
	public Fragment provideFragment() {
		return view;
	}

	@Provides
	@FragmentScope
	public ArtistListModel provideModel(
			ModelServiceModule serviceModule,
			ArtistDB artistDB,
			TrackDB trackDB,
			ArtistListImageLoader imageLoader) {
		return new ArtistListModel(serviceModule, artistDB, trackDB, imageLoader);
	}

	@Provides
	@FragmentScope
	public ArtistListPresenter providePresenter(ArtistListModel model, ArtistListView view) {
		return new ArtistListPresenter(model, view);
	}

	@Provides
	@FragmentScope
	public ArtistListAdapter provideAdapter(
			FragmentActivity activity, ArtistListPresenter presenter, SdkFactory sdkFactory) {
		return new ArtistListAdapter(activity, presenter, sdkFactory);
	}

	@Provides
	@FragmentScope
	public ArtistListImageLoader provideImageLoader(
			WebClient webClient,
			TestableBitmapFactory bitmapFactory,
			LastfmApi lastfmApi,
			ArtistListImageFileStorage fileStorage,
			ArtistListImageMemoryCache memoryCache) {
		return new ArtistListImageLoader(webClient, bitmapFactory, lastfmApi, fileStorage, memoryCache);
	}

	@Provides
	@FragmentScope
	public ArtistListImageFileStorage provideImageFileStorage(Context context) {
		return new ArtistListImageFileStorage(context);
	}

	@Provides
	@FragmentScope
	public ArtistListImageMemoryCache provideImageMemoryCache() {
		return new ArtistListImageMemoryCache();
	}
}
