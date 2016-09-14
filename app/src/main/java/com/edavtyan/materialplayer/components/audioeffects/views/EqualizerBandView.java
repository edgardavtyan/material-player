package com.edavtyan.materialplayer.components.audioeffects.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;

import lombok.Setter;

public class EqualizerBandView extends FrameLayout implements SeekBar.OnSeekBarChangeListener {
	private TextView frequencyView;
	private TextView gainView;
	private DoubleSeekbar bandView;

	private @Setter OnBandChangedListener onBandChangedListener;

	public interface OnBandChangedListener {
		void OnBandStopTracking();
		void onBandChanged(EqualizerBandView band, int gain);
	}

	public EqualizerBandView(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater.from(context).inflate(R.layout.partial_equalizer_band, this, true);

		frequencyView = (TextView) findViewById(R.id.frequency);
		gainView = (TextView) findViewById(R.id.gain);

		bandView = (DoubleSeekbar) findViewById(R.id.band);
		bandView.setOnSeekBarChangeListener(this);
	}

	public void setGainLimit(int gain) {
		bandView.setMax(gain);
	}

	public void setGain(int gain) {
		bandView.setProgress(gain);
		gainView.setText(getGainStr(gain));
	}

	public void setFrequency(int frequency) {
		int frequencyFormat;
		double frequencyConverted;

		boolean isKHz = frequency >= 1000;
		if (isKHz) {
			frequencyConverted = frequency / 1000f;

			boolean isWholeKHz = frequency % 1000 == 0;
			frequencyFormat = isWholeKHz
					? R.string.equalizer_frequency_khz_whole
					: R.string.equalizer_frequency_khz;
		} else {
			frequencyConverted = frequency;
			frequencyFormat = R.string.equalizer_frequency_hz;
		}

		frequencyView.setText(getResources().getString(frequencyFormat, frequencyConverted));
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		gainView.setText(getGainStr(progress));
		if (onBandChangedListener != null) onBandChangedListener.onBandChanged(this, progress);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		if (onBandChangedListener != null) onBandChangedListener.OnBandStopTracking();
	}

	private String getGainStr(int gain) {
		int gainStringFormatId = gain > 0
				? R.string.equalizer_format_gain_positive
				: R.string.equalizer_format_gain;

		return getResources().getString(gainStringFormatId, gain);
	}
}
