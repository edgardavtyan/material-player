package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.content.Context;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.base.BaseDialog;

public class PlaylistNewDialog extends BaseDialog {
	public PlaylistNewDialog(Context context) {
		super(context);
		setShowKeyboardEnabled(true);
	}

	@Override
	public int getDialogTitleRes() {
		return R.string.playlist_new_title;
	}

	@Override
	public int getPositiveButtonTextRes() {
		return R.string.playlist_new_action;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.dialog_preset_new;
	}
}
