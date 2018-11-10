package com.edavtyan.materialplayer.ui.audio_effects.presets;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ed.libsutils.utils.WindowUtils;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.ui.audio_effects.AudioEffectsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewPresetDialog {
	@BindView(R.id.edittext) EditText presetNameEditText;
	@BindView(R.id.error_exists) TextView presetErrorExistsView;

	private final AlertDialog dialog;
	private final Context context;
	private final AudioEffectsPresenter presenter;

	@SuppressWarnings("FieldCanBeLocal")
	private final View.OnClickListener positiveButtonListener
			= new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			presenter.onNewPresetDialogOkButtonClicked(presetNameEditText.getText().toString());
		}
	};

	@SuppressWarnings({"FieldCanBeLocal", "Convert2Lambda"})
	private final DialogInterface.OnClickListener negativeButtonListener
			= new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			presenter.onNewPresetDialogCancelButtonClicked();
		}
	};

	public NewPresetDialog(Context context, AudioEffectsPresenter presenter) {
		this.context = context;
		this.presenter = presenter;

		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_preset_new, null, false);
		ButterKnife.bind(this, view);

		this.dialog = new AlertDialog.Builder(context)
				.setView(view)
				.setTitle(R.string.equalizer_presets_dialog_new_title)
				.setPositiveButton(android.R.string.ok, null)
				.setNegativeButton(android.R.string.cancel, negativeButtonListener)
				.create();
	}

	public void show() {
		dialog.show();
		dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(positiveButtonListener);
		presetNameEditText.requestFocus();
		WindowUtils.toggleSoftKeyboard(context);
	}

	public void dismiss() {
		WindowUtils.closeSoftKeyboard(context, presetNameEditText);
		dialog.dismiss();
		presetNameEditText.setText(null);
		presetErrorExistsView.setVisibility(View.GONE);
	}

	public void showPresetAlreadyExists() {
		presetErrorExistsView.setVisibility(View.VISIBLE);
	}
}
