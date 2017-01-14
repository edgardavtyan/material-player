package com.edavtyan.materialplayer.components.artist_detail;

import android.content.Context;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.album_all.AlbumListAdapter;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.lib.lastfm.LastfmArtistInfoFileStorage;
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

	public ArtistDetailMvp.Model getModel() {
		if (model == null) {
			AlbumDB albumDB = new AlbumDB(getContext());
			TrackDB trackDB = new TrackDB(getContext());
			ArtistDB artistDB = new ArtistDB(getContext());
			model = new ArtistDetailModel(
					getContext(),
					artistDB,
					albumDB,
					trackDB,
					getCompactListPref(),
					getArtistArtLoader(),
					artistTitle);
		}

		return model;
	}

	public ArtistDetailMvp.View getView() {
		return view;
	}

	public ArtistDetailMvp.Presenter getPresenter() {
		if (presenter == null)
			presenter = new ArtistDetailPresenter(getModel(), getView());
		return presenter;
	}

	public AlbumListAdapter getAdapter() {
		if (adapter == null)
			adapter = new AlbumListAdapter(getContext(), getPresenter());
		return adapter;
	}

	public ArtistDetailImageLoader getArtistArtLoader() {
		if (artistDetailImageLoader == null)
			artistDetailImageLoader = new ArtistDetailImageLoader(
					getWebClient(),
					getLastfmApi(),
					getBitmapFactory(),
					getFileStorage(),
					getMemoryCache());
		return artistDetailImageLoader;
	}

	public ArtistDetailImageFileStorage getFileStorage() {
		if (fileStorage == null)
			fileStorage = new ArtistDetailImageFileStorage();
		return fileStorage;
	}

	public ArtistDetailImageMemoryCache getMemoryCache() {
		if (memoryCache == null)
			memoryCache = new ArtistDetailImageMemoryCache();
		return memoryCache;
	}

	public LastfmApi getLastfmApi() {
		if (lastfmApi == null)
			lastfmApi = new LastfmApi(
					getWebClient(),
					new LastfmArtistInfoFileStorage(),
					getContext().getString(R.string.lastfm_api_key));
		return lastfmApi;
	}
}
