package com.edavtyan.materialplayer.components.artist_mvp;

import android.content.Context;

import com.edavtyan.materialplayer.components.album_mvp.AlbumListAdapter;
import com.edavtyan.materialplayer.lib.db.AlbumDB;
import com.edavtyan.materialplayer.lib.db.ArtistDB;
import com.edavtyan.materialplayer.lib.db.TrackDB;

public class ArtistDetailDI {
	private final Context context;
	private final String artistTitle;
	private ArtistDetailMvp.Model model;
	private ArtistDetailMvp.View view;
	private ArtistDetailMvp.Presenter presenter;
	private AlbumListAdapter adapter;

	public ArtistDetailDI(Context context, ArtistDetailMvp.View view, String artistTitle) {
		this.context = context;
		this.view = view;
		this.artistTitle = artistTitle;
	}

	public ArtistDetailMvp.Model provideModel() {
		if (model == null) {
			AlbumDB albumDB = new AlbumDB(context);
			TrackDB trackDB = new TrackDB(context);
			ArtistDB artistDB = new ArtistDB(context);
			model = new ArtistDetailModel(context, artistDB, albumDB, trackDB, artistTitle);
		}

		return model;
	}

	public ArtistDetailMvp.View provideView() {
		return view;
	}

	public ArtistDetailMvp.Presenter providePresenter() {
		if (presenter == null) presenter = new ArtistDetailPresenter(provideModel(), provideView());
		return presenter;
	}

	public AlbumListAdapter provideAdapter() {
		if (adapter == null) adapter = new AlbumListAdapter(context, providePresenter());
		return adapter;
	}
}
