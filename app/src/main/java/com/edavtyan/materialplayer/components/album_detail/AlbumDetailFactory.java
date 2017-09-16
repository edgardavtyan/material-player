package com.edavtyan.materialplayer.components.album_detail;

import android.content.Context;

import com.edavtyan.materialplayer.db.DBModule;
import com.edavtyan.materialplayer.components.lists.lib.ListFactory;

public class AlbumDetailFactory extends ListFactory {
	private final AlbumDetailMvp.View view;
	private final int albumId;
	private final DBModule dbModule;
	private AlbumDetailAdapter adapter;
	private AlbumDetailMvp.Presenter presenter;
	private AlbumDetailMvp.Model model;

	public AlbumDetailFactory(Context context, AlbumDetailMvp.View view, int albumId) {
		super(context);
		this.view = view;
		this.albumId = albumId;
		this.dbModule = new DBModule(context);
	}

	public AlbumDetailMvp.View getView() {
		return view;
	}

	public AlbumDetailMvp.Model getModel() {
		if (model == null)
			model = new AlbumDetailModel(
					getContext(),
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

	public AlbumDetailMvp.Presenter getPresenter() {
		if (presenter == null)
			presenter = new AlbumDetailPresenter(getModel(), getView());
		return presenter;
	}
}
