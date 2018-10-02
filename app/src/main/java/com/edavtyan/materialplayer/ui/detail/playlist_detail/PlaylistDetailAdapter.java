package com.edavtyan.materialplayer.ui.detail.playlist_detail;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.ui.lists.lib.simple.SimpleTextListAdapter;

public class PlaylistDetailAdapter extends SimpleTextListAdapter<PlaylistDetailViewHolder> {
	private final PlaylistDetailPresenter presenter;

	public PlaylistDetailAdapter(Context context, PlaylistDetailPresenter presenter) {
		super(context, presenter);
		this.presenter = presenter;
	}

	@Override
	public PlaylistDetailViewHolder onCreateViewHolder(Context context, View view) {
		return new PlaylistDetailViewHolder(view, presenter);
	}
}
