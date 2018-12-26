package com.edavtyan.materialplayer.ui.audio_effects.presets;

import android.content.Context;
import android.widget.EditText;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.base.BaseDialog;
import com.edavtyan.materialplayer.ui.audio_effects.AudioEffectsPresenter;

import butterknife.BindView;

public class NewPresetDialog extends BaseDialog {
	@BindView(R.id.edittext) EditText presetNameEditText;

	private final AudioEffectsPresenter presenter;

	public NewPresetDialog(Context context, AudioEffectsPresenter presenter) {
		super(context);
		this.presenter = presenter;
		setShowKeyboardEnabled(true);

	}

	@Override
	public int getDialogTitleRes() {
		return R.string.equalizer_presets_dialog_new_title;
	}

	@Override
	public int getPositiveButtonTextRes() {
		return android.R.string.ok;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.dialog_preset_new;
	}

	@Override
	public void onShow() {
		presetNameEditText.requestFocus();
	}

	@Override
	public void onDismiss() {
		presetNameEditText.setText(null);
	}

	@Override
	public void onPositiveButtonClick() {
		presenter.onNewPresetDialogOkButtonClicked(presetNameEditText.getText().toString());
	}

	@Override
	public void onNegativeButtonClick() {
		presenter.onNewPresetDialogCancelButtonClicked();
	}

	public void show(String presetName) {
		presetNameEditText.setText(presetName);
		presetNameEditText.selectAll();
		show();
	}
}
