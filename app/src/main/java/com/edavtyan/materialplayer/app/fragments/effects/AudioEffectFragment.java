package com.edavtyan.materialplayer.app.fragments.effects;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.fragments.base.ServiceFragment;
import com.edavtyan.materialplayer.app.music.effects.StrengthBasedEffect;

public abstract class AudioEffectFragment
        extends ServiceFragment
        implements SeekBar.OnSeekBarChangeListener {
    private StrengthBasedEffect effect;
    private SeekBar seekbar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_titled_seekbar, container, false);

        TextView titleView = (TextView) view.findViewById(R.id.title);
        titleView.setText(getResources().getString(getTitleId()));

        seekbar = (SeekBar) view.findViewById(R.id.seekbar);
        seekbar.setOnSeekBarChangeListener(this);

        return view;
    }

    /*
     * Public methods
     */

    public abstract int getTitleId();
    public abstract SingleLevelEffectType getEffectType();

    /*
     * ServiceConnection
     */

    @Override
    public void onServiceConnected() {
        switch (getEffectType()) {
            case EFFECT_SURROUND:
                effect = getService().getSurround();
                break;

            case EFFECT_AMPLIFIER:
                effect = getService().getAmplifier();
                break;

            case EFFECT_BASS_BOOST:
                effect = getService().getBassBoost();
                break;
        }

        seekbar.setMax(effect.getMaxStrength());
        seekbar.setProgress(effect.getStrength());
    }

    /*
     * CompoundButton.OnCheckedChangeListener
     */

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (!fromUser) return;
        effect.setStrength(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        effect.saveSettings();
    }
}

