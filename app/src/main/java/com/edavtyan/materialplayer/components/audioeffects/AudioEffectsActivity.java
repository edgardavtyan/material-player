package com.edavtyan.materialplayer.components.audioeffects;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.edavtyan.materialplayer.MusicPlayerService;
import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.audioeffects.models.Amplifier;
import com.edavtyan.materialplayer.components.audioeffects.models.BassBoost;
import com.edavtyan.materialplayer.components.audioeffects.models.Surround;
import com.edavtyan.materialplayer.components.audioeffects.models.equalizer.Equalizer;
import com.edavtyan.materialplayer.lib.activities.BaseToolbarActivity;
import com.edavtyan.materialplayer.components.audioeffects.views.EqualizerView;
import com.edavtyan.materialplayer.components.audioeffects.views.TitledSeekbar;

public class AudioEffectsActivity
		extends BaseToolbarActivity
		implements ServiceConnection,
		           EqualizerView.OnBandChangedListener,
		           TitledSeekbar.OnProgressChangedListener,
		           CompoundButton.OnCheckedChangeListener {
	private Switch equalizerSwitch;
	private EqualizerView equalizerView;
	private TitledSeekbar bassBoostView;
	private TitledSeekbar amplifierView;
	private TitledSeekbar surroundView;

	private Equalizer equalizer;
	private BassBoost bassBoost;
	private Amplifier amplifier;
	private Surround surround;

	/* Activity */

	public static void startActivity(Context context) {
		context.startActivity(new Intent(context, AudioEffectsActivity.class));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		equalizerView = (EqualizerView) findViewById(R.id.equalizer);
		equalizerView.setOnBandChangedListener(this);

		equalizerSwitch = (Switch) findViewById(R.id.equalizerSwitch);
		equalizerSwitch.setOnCheckedChangeListener(this);

		bassBoostView = (TitledSeekbar) findViewById(R.id.bassBoost);
		bassBoostView.setOnProgressChangedListener(this);

		amplifierView = (TitledSeekbar) findViewById(R.id.amplifier);
		amplifierView.setOnProgressChangedListener(this);

		surroundView = (TitledSeekbar) findViewById(R.id.surround);
		surroundView.setOnProgressChangedListener(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		bindService(new Intent(this, MusicPlayerService.class), this, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		super.onStop();
		unbindService(this);
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_effects;
	}

	@Override
	protected int getToolbarTitleStringId() {
		return R.string.audio_effects_title;
	}

	/* Service connection */

	@Override
	public void onServiceConnected(ComponentName componentName, IBinder binder) {
		MusicPlayerService service = ((MusicPlayerService.MusicPlayerBinder) binder).getService();

		equalizer = service.getEqualizer();
		equalizerView.setGainLimit(equalizer.getGainLimit());
		equalizerView.setBands(
				equalizer.getBandsCount(),
				equalizer.getFrequencies(),
				equalizer.getGains());
		equalizerSwitch.setChecked(equalizer.isEnabled());

		bassBoost = service.getBassBoost();
		bassBoostView.setMax(bassBoost.getMaxStrength());
		bassBoostView.setProgress(bassBoost.getStrength());

		amplifier = service.getAmplifier();
		amplifierView.setMax(amplifier.getMaxStrength());
		amplifierView.setProgress(amplifier.getStrength());

		surround = service.getSurround();
		surroundView.setMax(surround.getMaxStrength());
		surroundView.setProgress(surround.getStrength());
	}

	@Override
	public void onServiceDisconnected(ComponentName componentName) {
	}

	/* View listeners */

	@Override
	public void onCheckedChanged(CompoundButton button, boolean isChecked) {
		equalizer.setEnabled(isChecked);
	}

	@Override
	public void onBandChanged(int band, int gain) {
		equalizer.setBandGain(band, gain);
	}

	@Override
	public void onBandStopTracking() {
		equalizer.saveSettings();
	}

	@Override
	public void onProgressChange(int seekbarId, int progress) {
		switch (seekbarId) {
		case R.id.bassBoost:
			bassBoost.setStrength(progress);
			break;
		case R.id.amplifier:
			amplifier.setStrength(progress);
			break;
		case R.id.surround:
			surround.setStrength(progress);
			break;
		}
	}

	@Override
	public void onStopTrackingTouch(int seekbarId) {
		switch (seekbarId) {
		case R.id.bassBoost:
			bassBoost.saveSettings();
			break;
		case R.id.amplifier:
			amplifier.saveSettings();
			break;
		case R.id.surround:
			surround.saveSettings();
			break;
		}
	}
}
