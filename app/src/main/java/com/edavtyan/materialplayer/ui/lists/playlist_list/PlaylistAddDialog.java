package com.edavtyan.materialplayer.ui.lists.playlist_list;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.edavtyan.materialplayer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Setter;

public class PlaylistAddDialog {
	@BindView(R.id.playlist_button_new) Button newButton;

	private final AlertDialog dialog;

	@SuppressWarnings("FieldCanBeLocal")
	private final DialogInterface.OnShowListener onShowListener
			= new DialogInterface.OnShowListener() {
		@Override
		public void onShow(DialogInterface dialogInterface) {
			dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(tint);
			dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(tint);
			newButton.getBackground().setColorFilter(tint, PorterDuff.Mode.SRC_ATOP);
		}
	};

	public interface OnNewPlaylistClickListener {
		void onNewPlaylistClick();
	}

	private @Setter OnNewPlaylistClickListener onNewPlaylistClickListener;

	private @Setter int tint;

	public PlaylistAddDialog(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_playlist_add, null, false);
		ButterKnife.bind(this, view);

		newButton.setOnClickListener(v -> {
			if (onNewPlaylistClickListener != null) {
				onNewPlaylistClickListener.onNewPlaylistClick();
			}
		});

		dialog = new AlertDialog.Builder(context)
				.setTitle(R.string.playlist_add_track_dialog_title)
				.setPositiveButton(R.string.playlist_add_dialog_positive, null)
				.setNegativeButton(android.R.string.cancel, null)
				.setView(view)
				.create();
		dialog.setOnShowListener(onShowListener);
	}

	public void show() {
		dialog.show();
	}
}
