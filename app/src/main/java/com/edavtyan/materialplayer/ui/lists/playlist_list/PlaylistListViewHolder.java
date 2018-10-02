package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.lists.lib.simple.SimpleTextListViewHolder;

public class PlaylistListViewHolder extends SimpleTextListViewHolder {
	private final PlaylistListPresenter presenter;

	public PlaylistListViewHolder(View itemView, PlaylistListPresenter presenter) {
		super(itemView);
		this.presenter = presenter;
	}

	@Override
	protected int getMenuRes() {
		return R.menu.menu_playlist;
	}

	@Override
	protected void onItemClick() {
		super.onItemClick();
		presenter.onPlaylistClick(getAdapterPositionNonFinal());
	}
}
