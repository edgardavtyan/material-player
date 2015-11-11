package com.edavtyan.materialplayer.app.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;

public class EqualizerBandView extends FrameLayout implements SeekBar.OnSeekBarChangeListener {
    private int gainLimit;

    TextView frequencyView;
    TextView gainView;
    SeekBar bandView;

    public EqualizerBandView(Context context) {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.layout_equalizer_band, this, true);

        frequencyView = (TextView) findViewById(R.id.frequency);
        gainView = (TextView) findViewById(R.id.gain);

        bandView = (SeekBar) findViewById(R.id.band);
        bandView.setOnSeekBarChangeListener(this);
    }

    /*
     * Public methods
     */

    public void setGainLimit(int gain) {
        gainLimit = gain * 2;
        bandView.setMax(gainLimit);
    }

    public void setGain(int gain) {
        bandView.setProgress(gain + (gainLimit / 2));
        gainView.setText(getGainStr(gain));
    }

    public void setFrequency(int frequency) {
        frequencyView.setText(getResources().getString(
                R.string.equalizer_format_band,
                hertzToGigahertz(frequency)));
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
        progress -= bandView.getMax();
        return progress;
    }

    private int hertzToGigahertz(int hertz) {
        return hertz / 1000;
    }

    private String getGainStr(int gain) {
        return getResources().getString(
                R.string.equalizer_format_gain, gain);
    }
}
