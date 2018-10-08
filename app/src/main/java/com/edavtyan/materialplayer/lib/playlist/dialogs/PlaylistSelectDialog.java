package com.edavtyan.materialplayer.lib.playlist.dialogs;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.base.BaseDialog;

import butterknife.BindView;
import lombok.Setter;

public class PlaylistSelectDialog extends BaseDialog {
	@BindView(R.id.list) RecyclerView list;

	private final PlaylistSelectDialogAdapter adapter;

	private @Setter PlaylistSelectDialogViewHolder.OnClickListener onPlaylistClickListener;
	private @Setter View.OnClickListener onNewPlaylistClickListener;

	public PlaylistSelectDialog(Context context) {
		super(context);

		adapter = new PlaylistSelectDialogAdapter(context);
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
		return R.string.playlist_select_title;
	}

	@Override
	public int getNeutralButtonTextRes() {
		return R.string.playlist_select_createNew;
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
