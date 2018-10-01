package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.edavtyan.materialplayer.R;

import butterknife.ButterKnife;
import lombok.Setter;

public class PlaylistNewDialog {
	private final AlertDialog dialog;

	@SuppressWarnings("FieldCanBeLocal")
	private final DialogInterface.OnShowListener onShowListener
			= new DialogInterface.OnShowListener() {
		@Override
		public void onShow(DialogInterface dialogInterface) {
			dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(tint);
			dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(tint);
		}
	};

	private @Setter int tint;

	public PlaylistNewDialog(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_preset_new, null, false);
		ButterKnife.bind(this, view);

		dialog = new AlertDialog.Builder(context)
				.setTitle(R.string.playlist_new_title)
				.setPositiveButton(R.string.playlist_new_action, null)
				.setNegativeButton(android.R.string.cancel, null)
				.setView(view)
				.create();
		dialog.setOnShowListener(onShowListener);
	}

	public void show() {
		dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		dialog.show();
	}
}
