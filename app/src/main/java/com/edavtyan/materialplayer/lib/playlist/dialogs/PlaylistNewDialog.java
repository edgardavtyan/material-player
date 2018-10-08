package com.edavtyan.materialplayer.lib.playlist.dialogs;

import android.content.Context;
import android.widget.EditText;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.base.BaseDialog;

import butterknife.BindView;
import lombok.Setter;

public class PlaylistNewDialog extends BaseDialog {
	@BindView(R.id.preset_edit_text) EditText editTextView;

	public interface OnConfirmListener {
		void onConfirm(String playlistName);
	}

	private @Setter OnConfirmListener onConfirmListener;

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

	@Override
	public void onConfirm() {
		if (onConfirmListener != null) {
			onConfirmListener.onConfirm(editTextView.getText().toString());
		}
	}
}
