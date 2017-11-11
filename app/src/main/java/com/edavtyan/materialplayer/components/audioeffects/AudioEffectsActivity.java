package com.edavtyan.materialplayer.components.audioeffects;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.audioeffects.models.PresetsAdapter;
import com.edavtyan.materialplayer.components.audioeffects.views.EqualizerBandView;
import com.edavtyan.materialplayer.components.audioeffects.views.EqualizerView;
import com.edavtyan.materialplayer.components.audioeffects.views.TitledSeekbar;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;

import java.util.List;

import butterknife.BindView;

public class AudioEffectsActivity
		extends BaseToolbarActivity
		implements AudioEffectsMvp.View,
				   CompoundButton.OnCheckedChangeListener,
				   TitledSeekbar.OnProgressChangedListener,
				   EqualizerView.OnBandChangedListener {

	@BindView(R.id.equalizer_switch) SwitchCompat equalizerSwitch;
	@BindView(R.id.equalizer) EqualizerView equalizerView;
	@BindView(R.id.presets_spinner) Spinner presetsView;
	@BindView(R.id.bass_boost) TitledSeekbar bassBoostView;
	@BindView(R.id.surround) TitledSeekbar surroundView;
	@BindView(R.id.amplifier) TitledSeekbar amplifierView;

	private AudioEffectsMvp.Presenter presenter;
	private PresetsAdapter presetsAdapter;

	private boolean presetsSpinnerFirstLaunch = true;

	private AdapterView.OnItemSelectedListener onPresetSelectedListener
			= new AdapterView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			if (presetsSpinnerFirstLaunch) {
				presetsSpinnerFirstLaunch = false;
				return;
			}

			presenter.onPresetSelected(position);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {

		}
	};

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		presetsAdapter = new PresetsAdapter(this);
		presetsView.setAdapter(presetsAdapter);
		presetsView.setOnItemSelectedListener(onPresetSelectedListener);

		equalizerSwitch.setOnCheckedChangeListener(this);
		equalizerView.setOnBandChangedListener(this);
		bassBoostView.setOnProgressChangedListener(this);
		surroundView.setOnProgressChangedListener(this);
		amplifierView.setOnProgressChangedListener(this);

		App app = (App) getApplicationContext();
		AudioEffectsFactory factory = app.getAudioEffectsFactory(this, this);
		presenter = factory.getPresenter();
		presenter.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.onDestroy();
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_effects;
	}

	@Override
	public void setEqualizerEnabled(boolean enabled) {
		equalizerSwitch.setChecked(enabled);
		equalizerSwitch.jumpDrawablesToCurrentState();
	}

	@Override
	public void setEqualizerBands(int count, int gainLimit, int[] frequencies, int[] gains) {
		equalizerView.setBands(count, frequencies, gains, gainLimit);
	}

	@Override
	public void setEqualizerPresets(List<String> presets) {
		presetsAdapter.setBuiltInPresets(presets);
	}

	@Override
	public void setEquqlizerPresetAsCustomNew() {
		presetsView.setSelection(0);
	}

	@Override
	public void initBassBoost(int max, int strength) {
		bassBoostView.setMax(max);
		bassBoostView.setProgress(strength);
	}

	@Override
	public void initSurround(int max, int strength) {
		surroundView.setMax(max);
		surroundView.setProgress(strength);
	}

	@Override
	public void initAmplifier(int max, int gain) {
		amplifierView.setMax(max);
		amplifierView.setProgress(gain);
	}

	@Override
	public void setCurrentEqualizerPreset(int presetIndex) {
		presetsView.setSelection(presetIndex);
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
}
