package com.edavtyan.materialplayer.components.audioeffects;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.edavtyan.materialplayer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewPresetDialog {
	@BindView(R.id.preset_edit_text) EditText presetNameEditText;

	private final AlertDialog dialog;
	private final AudioEffectsMvp.Presenter presenter;

	@SuppressWarnings("FieldCanBeLocal")
	private final DialogInterface.OnClickListener positiveButtonListener
			= new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			presenter.onCreateNewPreset(presetNameEditText.getText().toString());
		}
	};

	public NewPresetDialog(Context context, AudioEffectsMvp.Presenter presenter) {
		this.presenter = presenter;

		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_preset_new, null, false);
		ButterKnife.bind(this, view);

		this.dialog = new AlertDialog.Builder(context)
				.setView(view)
				.setTitle(R.string.equalizer_presets_dialog_new_title)
				.setPositiveButton(android.R.string.ok, positiveButtonListener)
				.setNegativeButton(android.R.string.cancel, null)
				.create();
	}

	public void show() {
		dialog.show();
	}
}
