package com.edavtyan.materialplayer.ui.detail.playlist_detail;

import android.view.MenuItem;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.lists.lib.simple.SimpleTextListViewHolder;

public class PlaylistDetailViewHolder extends SimpleTextListViewHolder {
	private final PlaylistDetailPresenter presenter;

	public PlaylistDetailViewHolder(View itemView, PlaylistDetailPresenter presenter) {
		super(itemView);
		this.presenter = presenter;
	}

	@Override
	protected int getMenuRes() {
		return R.menu.menu_playlist;
	}

	@Override
	protected boolean onMenuItemClick(MenuItem menuItem) {
		switch (menuItem.getItemId()) {
		case R.id.menu_remove:
			presenter.onRemoveFromPlaylistMenuClick(getAdapterPositionNonFinal());
			return true;
		}

		return super.onMenuItemClick(menuItem);
	}
}
