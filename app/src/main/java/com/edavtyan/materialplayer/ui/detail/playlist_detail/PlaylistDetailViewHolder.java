package com.edavtyan.materialplayer.ui.detail.playlist_detail;

import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.lists.lib.simple.SimpleTextListViewHolder;

public class PlaylistDetailViewHolder extends SimpleTextListViewHolder {
	public PlaylistDetailViewHolder(View itemView) {
		super(itemView);
	}

	@Override
	protected int getMenuRes() {
		return R.menu.menu_playlist;
	}
}
