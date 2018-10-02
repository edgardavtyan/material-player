package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.base.BaseDialog;

import butterknife.BindView;
import lombok.Setter;

public class PlaylistAddDialog extends BaseDialog {
	@BindView(R.id.list) RecyclerView list;

	private final PlaylistAddDialogListAdapter adapter;

	private @Setter PlaylistAddDialogViewHolder.OnClickListener onPlaylistClickListener;
	private @Setter View.OnClickListener onNewPlaylistClickListener;

	public PlaylistAddDialog(Context context) {
		super(context);

		adapter = new PlaylistAddDialogListAdapter(context);
		adapter.setOnClickListener(p -> onPlaylistClickListener.onClick(p));
		list.setAdapter(adapter);
		list.setLayoutManager(new LinearLayoutManager(context));
	}

	public void show(String[] playlistNames) {
		adapter.update(playlistNames);
		show();
	}

	@Override
	public int getDialogTitleRes() {
		return R.string.playlist_add_title;
	}

	@Override
	public int getPositiveButtonTextRes() {
		return R.string.playlist_add_action;
	}

	@Override
	public int getNeutralButtonTextRes() {
		return R.string.playlist_add_createNew;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.dialog_playlist_add;
	}

	@Override
	public void onNeutral() {
		onNewPlaylistClickListener.onClick(null);
	}
}
