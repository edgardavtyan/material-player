package com.edavtyan.materialplayer.ui.detail.playlist_detail;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.ui.lists.lib.simple.SimpleTextListAdapter;

public class PlaylistDetailAdapter extends SimpleTextListAdapter<PlaylistDetailViewHolder> {
	public PlaylistDetailAdapter(Context context, PlaylistDetailPresenter presenter) {
		super(context, presenter);
	}

	@Override
	public PlaylistDetailViewHolder onCreateViewHolder(Context context, View view) {
		return new PlaylistDetailViewHolder(view);
	}
}
