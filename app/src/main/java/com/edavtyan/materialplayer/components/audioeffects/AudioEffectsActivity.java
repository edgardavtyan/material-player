package com.edavtyan.materialplayer.components.audioeffects;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;

import com.edavtyan.materialplayer.App;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.audioeffects.views.EqualizerBandView;
import com.edavtyan.materialplayer.components.audioeffects.views.EqualizerView;
import com.edavtyan.materialplayer.components.audioeffects.views.TitledSeekbar;
import com.edavtyan.materialplayer.lib.base.BaseToolbarActivity;

public class AudioEffectsActivity
		extends BaseToolbarActivity
		implements AudioEffectsMvp.View,
				   CompoundButton.OnCheckedChangeListener,
				   TitledSeekbar.OnProgressChangedListener,
				   EqualizerView.OnBandChangedListener {

	private AudioEffectsMvp.Presenter presenter;
	private SwitchCompat equalizerSwitch;
	private EqualizerView equalizerView;
	private TitledSeekbar bassBoostView;
	private TitledSeekbar surroundView;
	private TitledSeekbar amplifierView;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		equalizerSwitch = findView(R.id.equalizerSwitch);
		equalizerSwitch.setOnCheckedChangeListener(this);

		equalizerView = findView(R.id.equalizer);
		equalizerView.setOnBandChangedListener(this);

		bassBoostView = findView(R.id.bassBoost);
		bassBoostView.setOnProgressChangedListener(this);

		surroundView = findView(R.id.surround);
		surroundView.setOnProgressChangedListener(this);

		amplifierView = findView(R.id.amplifier);
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
		case R.id.bassBoost:
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
		case R.id.bassBoost:
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
