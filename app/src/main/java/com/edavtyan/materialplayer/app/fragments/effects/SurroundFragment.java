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
import com.edavtyan.materialplayer.app.music.effects.surround.Surround;

public class SurroundFragment
        extends ServiceFragment
        implements SeekBar.OnSeekBarChangeListener {
    private Surround surround;
    private SeekBar seekBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_titled_seekbar, container, false);

        TextView titleView = (TextView) view.findViewById(R.id.title);
        titleView.setText(R.string.surround_title);

        seekBar = (SeekBar) view.findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(this);

        return view;
    }

    /*
     * ServiceConnection
     */

    @Override
    public void onServiceConnected() {
        surround = getService().getSurround();
        seekBar.setMax(surround.getMaxStrength());
        seekBar.setProgress(surround.getStrength());
    }

    /*
     * CompoundButton.OnCheckedChangeListener
     */

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (!fromUser) return;
        surround.setStrength(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        surround.saveSettings();
    }
}

