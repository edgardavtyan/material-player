package com.edavtyan.materialplayer.app.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.music.effects.equalizer.Equalizer;
import com.edavtyan.materialplayer.app.services.MusicPlayerService;
import com.edavtyan.materialplayer.app.views.EqualizerView;

public class AudioEffectsActivity
        extends AppCompatActivity
        implements ServiceConnection, EqualizerView.OnBandChangedListener {
    private Equalizer equalizer;
    private EqualizerView equalizerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effects);

        equalizerView = (EqualizerView) findViewById(R.id.equalizer);
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
        for (int i = 0; i < equalizer.getBandsCount(); i++) {
            equalizerView.addBand(equalizer.getBandFrequency(i), equalizer.getBandGain(i));
        }
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
}
