package com.edavtyan.materialplayer.components.audio_effects;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.ed.libsutils.views.CustomSwitchCompat;
import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.Equalizer;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.presets.NewPresetDialog;
import com.edavtyan.materialplayer.components.audio_effects.equalizer.presets.PresetsSpinnerView;
import com.edavtyan.materialplayer.components.audio_effects.views.EqualizerBandView;
import com.edavtyan.materialplayer.components.audio_effects.views.EqualizerView;
import com.edavtyan.materialplayer.components.audio_effects.views.TitledSeekbar;
import com.edavtyan.materialplayer.lib.theme.ScreenThemeModule;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.modular.activity.ActivityModulesFactory;
import com.edavtyan.materialplayer.modular.activity.ModularActivity;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityBaseMenuModule;
import com.edavtyan.materialplayer.modular.activity.modules.ActivityToolbarModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AudioEffectsActivity
		extends ModularActivity
		implements CompoundButton.OnCheckedChangeListener,
				   TitledSeekbar.OnProgressChangedListener,
				   EqualizerView.OnBandChangedListener {

	@BindView(R.id.equalizer_switch) CustomSwitchCompat equalizerSwitch;
	@BindView(R.id.equalizer) EqualizerView equalizerView;
	@BindView(R.id.preset_new) Button newPresetButton;
	@BindView(R.id.preset_remove) Button deletePresetButton;
	@BindView(R.id.bass_boost) TitledSeekbar bassBoostView;
	@BindView(R.id.surround) TitledSeekbar surroundView;
	@BindView(R.id.amplifier) TitledSeekbar amplifierView;

	@Inject ScreenThemeModule themeModule;
	@Inject ActivityBaseMenuModule baseMenuModule;
	@Inject ActivityToolbarModule toolbarModule;
	@Inject AudioEffectsPresenter presenter;
	@Inject NewPresetDialog newPresetDialog;
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

		if (isAmplifierAvailable()) {
			amplifierView.setOnProgressChangedListener(this);
		} else {
			amplifierView.setVisibility(View.GONE);
		}

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

	@Override
	public void onThemeChanged(ThemeColors colors) {
		super.onThemeChanged(colors);
		ViewCompat.setBackgroundTintList(
				newPresetButton,
				ColorStateList.valueOf(colors.getColorPrimary()));
		equalizerSwitch.setTint(colors.getColorPrimary());
		equalizerView.setTint(colors.getColorPrimary());
		bassBoostView.setTint(colors.getColorPrimary());
		surroundView.setTint(colors.getColorPrimary());
		amplifierView.setTint(colors.getColorPrimary());
		newPresetDialog.setTint(colors.getColorPrimary());
		presetsSpinner.setTint(colors.getColorPrimary());
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

	public void showNewPresetCreationDialog() {
		newPresetDialog.show();
	}

	public void closeNewPresetCreationDialog() {
		newPresetDialog.dismiss();
	}

	public void showPresetAlreadyExists() {
		newPresetDialog.showPresetAlreadyExists();
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

	public void initAmplifier(int max, int gain) {
		if (!isAmplifierAvailable()) return;
		amplifierView.setMax(max);
		amplifierView.setProgress(gain);
	}

	public void setCurrentEqualizerPreset(int presetIndex, Equalizer.PresetType presetType) {
		presetsSpinner.selectPresetAt(presetIndex, presetType);
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
		case R.id.amplifier:
			presenter.onAmplifierStrengthChanged(progress);
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
		case R.id.amplifier:
			presenter.onAmplifierStrengthStopChanging();
			break;
		}
	}

	protected AudioEffectsViewComponent getComponent() {
		return DaggerAudioEffectsViewComponent
				.builder()
				.appComponent(((App)getApplication()).getAppComponent())
				.activityModulesFactory(new ActivityModulesFactory())
				.audioEffectsViewFactory(new AudioEffectsViewFactory(this))
				.build();
	}

	private boolean isAmplifierAvailable() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
	}
}
