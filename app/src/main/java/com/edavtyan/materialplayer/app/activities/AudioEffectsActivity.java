package com.edavtyan.materialplayer.app.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.music.HQEqualizerStats;
import com.edavtyan.materialplayer.app.music.adapters.EqualizerPresetsAdapter;
import com.edavtyan.materialplayer.app.views.EqualizerView;

public class AudioEffectsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effects);

        Spinner presetsSpinner = (Spinner) findViewById(R.id.presets);
        presetsSpinner.setAdapter(new EqualizerPresetsAdapter(this));

        EqualizerView equalizerView = (EqualizerView) findViewById(R.id.equalizer);
        equalizerView.setGainLimit(HQEqualizerStats.GAIN_LIMIT);
        for (int i = 0; i < HQEqualizerStats.BANDS_COUNT; i++) {
            equalizerView.addBand(HQEqualizerStats.FREQUENCIES[i], HQEqualizerStats.DEFAULT_GAIN);
        }
    }
}
