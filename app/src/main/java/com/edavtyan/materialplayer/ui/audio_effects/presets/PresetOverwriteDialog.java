package com.edavtyan.materialplayer.ui.audio_effects.presets;

import android.content.Context;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.base.BaseDialog;
import com.edavtyan.materialplayer.ui.audio_effects.AudioEffectsPresenter;

public class PresetOverwriteDialog extends BaseDialog {
	private final AudioEffectsPresenter presenter;

	public PresetOverwriteDialog(Context context, AudioEffectsPresenter presenter) {
		super(context);
		this.presenter = presenter;
	}

	@Override
	public int getDialogTitleRes() {
		return R.string.equalizer_presets_dialog_overwrite_title;
	}

	@Override
	public int getPositiveButtonTextRes() {
		return R.string.equalizer_presets_dialog_overwrite_action;
	}

	@Override
	public int getMessageRes() {
		return R.string.equalizer_presets_dialog_overwrite_message;
	}

	@Override
	public void onPositiveButtonClick() {
		super.onPositiveButtonClick();
		presenter.onOverwriteDialogConfirmed();
	}
}
