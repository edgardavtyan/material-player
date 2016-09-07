package com.edavtyan.materialplayer.components.artist_all;

import android.content.Context;

import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.lib.base.BaseFactory;

public class ArtistListFactory extends BaseFactory {
	private final Context context;
	private final ArtistListMvp.View view;
	private ArtistListModel model;
	private ArtistDB artistDB;
	private ArtistListPresenter presenter;
	private ArtistListAdapter adapter;

	public ArtistListFactory(Context context, ArtistListMvp.View view) {
		super(context);
		this.context = context;
		this.view = view;
	}

	public ArtistListMvp.Model provideModel() {
		if (model == null) model = new ArtistListModel(provideArtistDB());
		return model;
	}

	public ArtistDB provideArtistDB() {
		if (artistDB == null) artistDB = new ArtistDB(context);
		return artistDB;
	}

	public ArtistListMvp.View provideView() {
		return view;
	}

	public ArtistListMvp.Presenter providePresenter() {
		if (presenter == null) presenter = new ArtistListPresenter(provideModel(), provideView());
		return presenter;
	}

	public ArtistListAdapter provideAdapter() {
		if (adapter == null) adapter = new ArtistListAdapter(context, providePresenter());
		return adapter;
	}
}
