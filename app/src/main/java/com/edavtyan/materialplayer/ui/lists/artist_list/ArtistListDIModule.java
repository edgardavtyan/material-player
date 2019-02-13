package com.edavtyan.materialplayer.ui.lists.artist_list;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.prefs.AdvancedGsonSharedPrefs;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.FragmentScope;
import com.edavtyan.materialplayer.ui.SdkFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ArtistListDIModule {
	private final Activity activity;
	private final ArtistListFragment view;

	public ArtistListDIModule(Activity activity, ArtistListFragment view) {
		this.activity = activity;
		this.view = view;
	}

	@Provides
	@FragmentScope
	public Activity provideActivity() {
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
			Activity activity, ArtistListPresenter presenter, SdkFactory sdkFactory) {
		return new ArtistListAdapter(activity, presenter, sdkFactory);
	}

	@Provides
	@FragmentScope
	public ArtistListImageLoader provideImageLoader(
			LastfmApi lastfmApi,
			ArtistListImageLinkCache linkCache) {
		return new ArtistListImageLoader(lastfmApi, linkCache);
	}

	@Provides
	@FragmentScope
	public ArtistListImageLinkCache provideArtistListImageLinkCache(AdvancedGsonSharedPrefs prefs) {
		return new ArtistListImageLinkCache(prefs);
	}
}
