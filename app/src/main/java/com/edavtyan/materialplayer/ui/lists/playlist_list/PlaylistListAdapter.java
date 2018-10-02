package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.lists.lib.ListAdapter;
import com.edavtyan.materialplayer.ui.lists.lib.ListPresenter;

public class PlaylistListAdapter extends ListAdapter<PlaylistListViewHolder> {
	public PlaylistListAdapter(Context context, ListPresenter<PlaylistListViewHolder> presenter) {
		super(context, presenter);
	}

	@Override
	public int getLayoutId() {
		return R.layout.listitem_track;
	}

	@Override
	public PlaylistListViewHolder onCreateViewHolder(Context context, View view) {
		return new PlaylistListViewHolder(view);
	}
}
