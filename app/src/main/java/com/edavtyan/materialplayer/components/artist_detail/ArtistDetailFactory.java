package com.edavtyan.materialplayer.components.artist_detail;

import android.content.Context;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.album_all.AlbumListAdapter;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.mvp.list.ListFactory;

public class ArtistDetailFactory extends ListFactory {
	private final String artistTitle;
	private final ArtistDetailMvp.View view;
	private ArtistDetailMvp.Model model;
	private ArtistDetailMvp.Presenter presenter;
	private AlbumListAdapter adapter;
	private ArtistDetailImageLoader artistDetailImageLoader;
	private LastfmApi lastfmApi;
	private ArtistDetailImageFileStorage fileStorage;
	private ArtistDetailImageMemoryCache memoryCache;

	public ArtistDetailFactory(Context context, ArtistDetailMvp.View view, String artistTitle) {
		super(context);
		this.view = view;
		this.artistTitle = artistTitle;
	}

	public ArtistDetailMvp.Model provideModel() {
		if (model == null) {
			AlbumDB albumDB = new AlbumDB(provideContext());
			TrackDB trackDB = new TrackDB(provideContext());
			ArtistDB artistDB = new ArtistDB(provideContext());
			model = new ArtistDetailModel(
					provideContext(),
					artistDB,
					albumDB,
					trackDB,
					provideCompactListPref(),
					provideArtistArtProvider(),
					artistTitle);
		}

		return model;
	}

	public ArtistDetailMvp.View provideView() {
		return view;
	}

	public ArtistDetailMvp.Presenter providePresenter() {
		if (presenter == null)
			presenter = new ArtistDetailPresenter(provideModel(), provideView());
		return presenter;
	}

	public AlbumListAdapter provideAdapter() {
		if (adapter == null)
			adapter = new AlbumListAdapter(provideContext(), providePresenter());
		return adapter;
	}

	public ArtistDetailImageLoader provideArtistArtProvider() {
		if (artistDetailImageLoader == null)
			artistDetailImageLoader = new ArtistDetailImageLoader(
					providerWebClient(),
					provideLastfmApi(),
					provideBitmapFactory(),
					provideFileStorage(),
					provideMemoryCache());
		return artistDetailImageLoader;
	}

	public ArtistDetailImageFileStorage provideFileStorage() {
		if (fileStorage == null)
			fileStorage = new ArtistDetailImageFileStorage();
		return fileStorage;
	}

	public ArtistDetailImageMemoryCache provideMemoryCache() {
		if (memoryCache == null)
			memoryCache = new ArtistDetailImageMemoryCache();
		return memoryCache;
	}

	public LastfmApi provideLastfmApi() {
		if (lastfmApi == null)
			lastfmApi = new LastfmApi(
					providerWebClient(),
					provideContext().getString(R.string.lastfm_api_key));
		return lastfmApi;
	}
}
