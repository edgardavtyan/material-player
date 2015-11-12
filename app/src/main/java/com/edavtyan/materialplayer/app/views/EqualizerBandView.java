package com.edavtyan.materialplayer.app.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.app.R;

import lombok.Setter;

public class EqualizerBandView extends FrameLayout implements SeekBar.OnSeekBarChangeListener {
    private @Setter OnBandChangedListener onBandChangedListener;

    private TextView frequencyView;
    private TextView gainView;
    private DoubleSeekbar bandView;


    public interface OnBandChangedListener {
        void OnBandStopTracking();
        void onBandChanged(EqualizerBandView band, int gain);
    }


    public EqualizerBandView(Context context) {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.layout_equalizer_band, this, true);

        frequencyView = (TextView) findViewById(R.id.frequency);
        gainView = (TextView) findViewById(R.id.gain);

        bandView = (DoubleSeekbar) findViewById(R.id.band);
        bandView.setOnSeekBarChangeListener(this);
    }

    /*
     * Public methods
     */

    public void setGainLimit(int gain) {
        bandView.setMax(gain);
    }

    public void setGain(int gain) {
        bandView.setProgress(gain);
        gainView.setText(getGainStr(gain));
    }

    public void setFrequency(int frequency) {
        int frequencyFormat;
        if (frequency >= 1000) {
            frequency /= 1000;
            frequencyFormat = R.string.equalizer_frequency_khz;
        } else {
            frequencyFormat = R.string.equalizer_frequency_hz;
        }

        frequencyView.setText(getResources().getString(frequencyFormat, frequency));
    }

    /*
     * OnSeekBarChangeListener
     */

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        gainView.setText(getGainStr(progress));
        if (onBandChangedListener != null) onBandChangedListener.onBandChanged(this, progress);
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

    private String getGainStr(int gain) {
        return (gain > 0 ? "+" : "") + getResources().getString(
                R.string.equalizer_format_gain, gain);
    }
}
