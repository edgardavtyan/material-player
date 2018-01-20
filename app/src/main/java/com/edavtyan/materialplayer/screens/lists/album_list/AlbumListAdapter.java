package com.edavtyan.materialplayer.screens.lists.album_list;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.screens.lists.lib.ListAdapter;

public class AlbumListAdapter extends ListAdapter<AlbumListViewHolder> {

	private final AlbumListPresenter presenter;

	public AlbumListAdapter(Context context, AlbumListPresenter presenter) {
		super(context, presenter);
		this.presenter = presenter;
	}

	@Override
	public int getNormalLayoutId() {
		return R.layout.listitem_album;
	}

	@Override
	public int getCompactLayoutId() {
		return R.layout.listitem_album_compact;
	}

	@Override
	public AlbumListViewHolder onCreateViewHolder(Context context, View view) {
		return new AlbumListViewHolder(context, view, presenter);
	}
}
