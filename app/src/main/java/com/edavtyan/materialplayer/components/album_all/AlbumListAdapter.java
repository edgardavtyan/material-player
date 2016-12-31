package com.edavtyan.materialplayer.components.album_all;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.mvp.list.ListAdapter;

public class AlbumListAdapter
		extends ListAdapter<AlbumListViewHolder>
		implements AlbumListViewHolder.OnHolderClickListener,
				   AlbumListViewHolder.OnHolderMenuItemClickListener {

	private final AlbumListMvp.Presenter presenter;

	public AlbumListAdapter(Context context, AlbumListMvp.Presenter presenter) {
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
		AlbumListViewHolder holder = new AlbumListViewHolder(context, view);
		holder.setOnHolderClickListener(this);
		holder.setOnHolderMenuItemClickListener(this);
		return holder;
	}

	@Override
	public void onHolderClick(AlbumListViewHolder holder) {
		presenter.onHolderClick(holder.getAdapterPositionNonFinal());
	}

	@Override
	public void onMenuAddToPlaylistClick(AlbumListViewHolder holder) {
		presenter.onAddToPlaylist(holder.getAdapterPositionNonFinal());
	}
}
