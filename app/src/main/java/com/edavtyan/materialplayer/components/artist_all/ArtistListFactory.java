package com.edavtyan.materialplayer.components.artist_all;

import android.content.Context;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.mvp.list.ListFactory;

public class ArtistListFactory extends ListFactory {
	private final ArtistListMvp.View view;
	private ArtistListModel model;
	private ArtistDB artistDB;
	private ArtistListPresenter presenter;
	private ArtistListAdapter adapter;
	private LastfmApi lastfmApi;
	private ArtistListImageFileStorage fileStorage;
	private ArtistListImageMemoryCache memoryCache;
	private ArtistListImageLoader imageLoader;

	public ArtistListFactory(Context context, ArtistListMvp.View view) {
		super(context);
		this.view = view;
	}

	public ArtistListMvp.View provideView() {
		return view;
	}

	public ArtistListMvp.Model provideModel() {
		if (model == null)
			model = new ArtistListModel(
					provideContext(),
					provideArtistDB(),
					provideImageLoader(),
					provideCompactListPref());
		return model;
	}

	public ArtistDB provideArtistDB() {
		if (artistDB == null) artistDB = new ArtistDB(provideContext());
		return artistDB;
	}

	public ArtistListMvp.Presenter providePresenter() {
		if (presenter == null)
			presenter = new ArtistListPresenter(provideModel(), provideView());
		return presenter;
	}

	public ArtistListAdapter provideAdapter() {
		if (adapter == null)
			adapter = new ArtistListAdapter(provideContext(), providePresenter());
		return adapter;
	}

	public LastfmApi provideLastfmApi() {
		if (lastfmApi == null)
			lastfmApi = new LastfmApi(
					providerWebClient(),
					provideContext().getString(R.string.lastfm_api_key));
		return lastfmApi;
	}

	public ArtistListImageLoader provideImageLoader() {
		if (imageLoader == null)
			imageLoader = new ArtistListImageLoader(
					provideLastfmApi(),
					provideFileStorage(),
					provideMemoryCache());
		return imageLoader;
	}

	public ArtistListImageFileStorage provideFileStorage() {
		if (fileStorage == null)
			fileStorage = new ArtistListImageFileStorage();
		return fileStorage;
	}

	public ArtistListImageMemoryCache provideMemoryCache() {
		if (memoryCache == null)
			memoryCache = new ArtistListImageMemoryCache();
		return memoryCache;
	}
}
