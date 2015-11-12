package com.edavtyan.materialplayer.app.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.activities.base.BaseToolbarActivity;
import com.edavtyan.materialplayer.app.music.effects.equalizer.Equalizer;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.views.EqualizerView;

public class AudioEffectsActivity
        extends BaseToolbarActivity
        implements ServiceConnection, EqualizerView.OnBandChangedListener,
                   CompoundButton.OnCheckedChangeListener {
    private Equalizer equalizer;
    private EqualizerView equalizerView;
    private Switch equalizerSwitch;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effects);
        initToolbar(R.string.audio_effects_title);

        equalizerView = (EqualizerView) findViewById(R.id.equalizer);
        equalizerSwitch = (Switch) findViewById(R.id.equalizer_switch);
        equalizerSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindService(
                new Intent(this, MusicPlayerService.class),
                this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }

    /*
     * ServiceConnection
     */

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        equalizer = ((MusicPlayerService.MusicPlayerBinder)iBinder).getService().getEqualizer();
        equalizerView.setGainLimit(equalizer.getGainLimit());
        equalizerView.setOnBandChangedListener(this);
        equalizerView.setBands(equalizer.getBandsCount(), equalizer.getFrequencies(),
                equalizer.getGains());

        equalizerSwitch.setChecked(equalizer.isEnabled());
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {}

    /*
     * EqualizerView.OnBandChangedListener
     */

    @Override
    public void onBandChanged(int band, int gain) {
        equalizer.setBandGain(band, gain);
    }

    @Override
    public void onBandStopTracking() {
        equalizer.saveSettings();
    }

    /*
     * CompoundButton.OnCheckedChangeListener
     */

    @Override
    public void onCheckedChanged(CompoundButton button, boolean isChecked) {
        equalizer.setEnabled(isChecked);
    }
}
