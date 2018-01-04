package com.edavtyan.materialplayer.components.detail.album_detail;

import android.content.Context;

import com.edavtyan.materialplayer.db.DBModule;
import com.edavtyan.materialplayer.components.lists.lib.ListFactory;

public class AlbumDetailFactory extends ListFactory {
	private final AlbumDetailView view;
	private final int albumId;
	private final DBModule dbModule;
	private AlbumDetailAdapter adapter;
	private AlbumDetailPresenter presenter;
	private AlbumDetailModel model;

	public AlbumDetailFactory(Context context, AlbumDetailView view, int albumId) {
		super(context);
		this.view = view;
		this.albumId = albumId;
		this.dbModule = new DBModule(context);
	}

	public AlbumDetailModel getModel() {
		if (model == null)
			model = new AlbumDetailModel(
					getModelServiceModule(),
					dbModule.getAlbumDB(),
					dbModule.getTrackDB(),
					getArtProvider(),
					getCompactListPref(),
					albumId);

		return model;
	}

	public AlbumDetailAdapter getAdapter() {
		if (adapter == null)
			adapter = new AlbumDetailAdapter(getContext(), getPresenter());
		return adapter;
	}

	public AlbumDetailPresenter getPresenter() {
		if (presenter == null)
			presenter = new AlbumDetailPresenter(getModel(), view);
		return presenter;
	}
}
