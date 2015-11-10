package com.edavtyan.materialplayer.app.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.music.HQEqualizerStats;

public class EqualizerBand extends FrameLayout implements SeekBar.OnSeekBarChangeListener {
    TextView frequencyView;
    TextView gainView;
    SeekBar bandView;

    public EqualizerBand(Context context, int frequency, int gain) {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.layout_equalizer_band, this, true);

        frequencyView = (TextView) findViewById(R.id.frequency);
        frequencyView.setText(getResources().getString(
                R.string.equalizer_format_band,
                hertzToGigahertz(frequency)));

        gainView = (TextView) findViewById(R.id.gain);
        gainView.setText(getGainStr(gain));

        bandView = (SeekBar) findViewById(R.id.band);
        bandView.setMax(millibelsToDecibels(HQEqualizerStats.MAX_GAIN * 2));
        bandView.setProgress(bandView.getMax() / 2);
        bandView.setOnSeekBarChangeListener(this);
    }

    /*
     * OnSeekBarChangeListener
     */

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        gainView.setText(getGainStr(progressToGain(progress)));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

    /*
     * Private methods
     */

    private int progressToGain(int progress) {
        progress -= millibelsToDecibels(HQEqualizerStats.MAX_GAIN);
        return progress;
    }

    private int millibelsToDecibels(int millibels) {
        return millibels / 100;
    }

    private int hertzToGigahertz(int hertz) {
        return hertz / 1000;
    }

    private String getGainStr(int gain) {
        return getResources().getString(
                R.string.equalizer_format_gain, gain);
    }
}
