package com.edavtyan.materialplayer.ui.audio_effects;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ed.libsutils.views.CustomSwitchCompat;
import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.base.BaseActivity;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;
import com.edavtyan.materialplayer.player.effects.equalizer.Equalizer;
import com.edavtyan.materialplayer.ui.audio_effects.presets.NewPresetDialog;
import com.edavtyan.materialplayer.ui.audio_effects.presets.PresetOverwriteDialog;
import com.edavtyan.materialplayer.ui.audio_effects.presets.PresetsSpinnerView;
import com.edavtyan.materialplayer.ui.audio_effects.views.EqualizerBandView;
import com.edavtyan.materialplayer.ui.audio_effects.views.EqualizerView;
import com.edavtyan.materialplayer.ui.audio_effects.views.TitledSeekbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AudioEffectsActivity
		extends BaseActivity
		implements CompoundButton.OnCheckedChangeListener,
				   TitledSeekbar.OnProgressChangedListener,
				   EqualizerView.OnBandChangedListener {

	@BindView(R.id.equalizer_switch) CustomSwitchCompat equalizerSwitch;
	@BindView(R.id.equalizer) EqualizerView equalizerView;
	@BindView(R.id.preset_new) Button newPresetButton;
	@BindView(R.id.preset_remove) Button deletePresetButton;
	@BindView(R.id.bass_boost) TitledSeekbar bassBoostView;
	@BindView(R.id.surround) TitledSeekbar surroundView;

	@BindView(R.id.error_notSupported) TextView errorNotSupportedView;

	@Inject ScreenThemeModule themeModule;
	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ActivityToolbarModule toolbarModule;
	@Inject AudioEffectsPresenter presenter;
	@Inject NewPresetDialog newPresetDialog;
	@Inject PresetOverwriteDialog presetOverwriteDialog;
	@Inject PresetsSpinnerView presetsSpinner;

	private final View.OnClickListener onNewPresetClicked = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			presenter.onCreateNewPresetButtonClicked();
		}
	};

	private final View.OnClickListener onDeletePresetClicked = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			presetsSpinner.deleteCurrentPreset();
		}
	};

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_effects);
		ButterKnife.bind(this);

		equalizerSwitch.setOnCheckedChangeListener(this);
		equalizerView.setOnBandChangedListener(this);
		bassBoostView.setOnProgressChangedListener(this);
		surroundView.setOnProgressChangedListener(this);
		newPresetButton.setOnClickListener(onNewPresetClicked);
		deletePresetButton.setOnClickListener(onDeletePresetClicked);

		getComponent().inject(this);
		addModule(baseMenuModule);
		addModule(toolbarModule);
		addModule(themeModule);

		presenter.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}

	public void setEqualizerEnabled(boolean enabled) {
		equalizerSwitch.setChecked(enabled);
		equalizerSwitch.jumpDrawablesToCurrentState();
	}

	public void setEqualizerBands(int count, int gainLimit, int[] frequencies, int[] gains) {
		equalizerView.setBands(count, frequencies, gains, gainLimit);
	}

	public void setEqualizerPresets(List<String> builtInPresets, List<String> customPresets) {
		presetsSpinner.setPresets(builtInPresets, customPresets);
	}

	public void setEqualizerPresetAsCustomNew() {
		presetsSpinner.setCurrentPresetAsCustomNew();
	}

	public void selectLastCustomPreset() {
		presetsSpinner.selectLastCustomPreset();
	}

	public void showNewPresetCreationDialog(String presetName) {
		newPresetDialog.show(presetName);
	}

	public void closeNewPresetCreationDialog() {
		newPresetDialog.close();
	}

	public void showPresetAlreadyExists() {
		presetOverwriteDialog.show();
	}

	public void setDeletePresetButtonEnabled(boolean enabled) {
		if (enabled) {
			deletePresetButton.setAlpha(1.0f);
			deletePresetButton.setEnabled(true);
		} else {
			deletePresetButton.setAlpha(0.5f);
			deletePresetButton.setEnabled(false);
		}
	}

	public void initBassBoost(int max, int strength) {
		bassBoostView.setMax(max);
		bassBoostView.setProgress(strength);
	}

	public void initSurround(int max, int strength) {
		surroundView.setMax(max);
		surroundView.setProgress(strength);
	}

	public void setCurrentEqualizerPreset(int presetIndex, Equalizer.PresetType presetType) {
		presetsSpinner.selectPresetAt(presetIndex, presetType);
	}

	public void setEqualizerNotSupported() {
		errorNotSupportedView.setVisibility(View.VISIBLE);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		presenter.onEqualizerEnabledChanged(isChecked);
	}

	@Override
	public void onBandChanged(EqualizerBandView band) {
		presenter.onEqualizerBandChanged(band);
	}

	@Override
	public void onBandStopTracking() {
		presenter.onEqualizerBandStopTracking();
	}

	@Override
	public void onProgressChange(int seekbarId, int progress) {
		switch (seekbarId) {
		case R.id.bass_boost:
			presenter.onBassBoostStrengthChanged(progress);
			break;
		case R.id.surround:
			presenter.onSurroundStrengthChanged(progress);
			break;
		}
	}

	@Override
	public void onStopTrackingTouch(int seekbarId) {
		switch (seekbarId) {
		case R.id.bass_boost:
			presenter.onBassBoostStrengthStopChanging();
			break;
		case R.id.surround:
			presenter.onSurroundStrengthStopChanging();
			break;
		}
	}

	protected AudioEffectsDIComponent getComponent() {
		return DaggerAudioEffectsDIComponent
				.builder()
				.appDIComponent(((App) getApplication()).getAppComponent())
				.audioEffectsDIModule(new AudioEffectsDIModule(this))
				.build();
	}
}
