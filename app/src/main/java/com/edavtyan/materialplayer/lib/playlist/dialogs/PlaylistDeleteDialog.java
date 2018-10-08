package com.edavtyan.materialplayer.lib.playlist.dialogs;

import android.content.Context;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.base.BaseDialog;
import com.edavtyan.materialplayer.ui.lists.playlist_list.PlaylistListPresenter;

public class PlaylistDeleteDialog extends BaseDialog {
	private final PlaylistListPresenter presenter;

	public PlaylistDeleteDialog(Context context, PlaylistListPresenter presenter) {
		super(context);
		this.presenter = presenter;
	}

	@Override
	public int getDialogTitleRes() {
		return R.string.playlist_delete_title;
	}

	@Override
	public int getPositiveButtonTextRes() {
		return R.string.playlist_delete_action;
	}

	@Override
	public int getMessageRes() {
		return R.string.playlist_delete_message;
	}

	@Override
	public void onConfirm() {
		super.onConfirm();
		presenter.onDeletePlaylistConfirm();
	}
}
