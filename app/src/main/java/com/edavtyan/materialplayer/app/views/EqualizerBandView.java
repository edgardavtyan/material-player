package com.edavtyan.materialplayer.app.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;

import lombok.Setter;

public class EqualizerBandView extends FrameLayout implements SeekBar.OnSeekBarChangeListener {
    private int gainLimit;
    private @Setter OnBandChangedListener onBandChangedListener;

    private TextView frequencyView;
    private TextView gainView;
    private SeekBar bandView;


    public interface OnBandChangedListener {
        void OnBandStopTracking();
        void onBandChanged(EqualizerBandView band, int gain);
    }


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
        frequencyView.setText(getResources().getString(R.string.equalizer_format_band, frequency));
    }

    /*
     * OnSeekBarChangeListener
     */

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int gain = progressToGain(progress);
        gainView.setText(getGainStr(gain));
        if (onBandChangedListener != null) onBandChangedListener.onBandChanged(this, gain);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (onBandChangedListener != null) onBandChangedListener.OnBandStopTracking();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

    /*
     * Private methods
     */

    private int progressToGain(int progress) {
        progress -= bandView.getMax() / 2;
        return progress;
    }

    private String getGainStr(int gain) {
        return getResources().getString(
                R.string.equalizer_format_gain, gain);
    }
}
