package com.edavtyan.materialplayer.components.audioeffects.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;

import lombok.Getter;
import lombok.Setter;

public class EqualizerBandView
		extends FrameLayout
		implements DoubleSeekbar.OnProgressChangedListener,
				   DoubleSeekbar.OnStopTrackingTouchListener {

	private final TextView frequencyView;
	private final TextView gainView;
	private final DoubleSeekbar bandView;

	private int frequency;
	private @Getter @Setter int index;
	private @Setter OnBandChangedListener onBandChangedListener;

	public interface OnBandChangedListener {
		void onBandStopTracking();
		void onBandChanged(EqualizerBandView band);
	}

	public EqualizerBandView(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater.from(context).inflate(R.layout.partial_equalizer_band, this, true);

		frequencyView = (TextView) findViewById(R.id.frequency);
		gainView = (TextView) findViewById(R.id.gain);

		bandView = (DoubleSeekbar) findViewById(R.id.band);
		bandView.setOnProgressChangedListener(this);
		bandView.setOnStopTrackingTouchListener(this);
	}

	public int getGainLimit() {
		return bandView.getMax();
	}

	public void setGainLimit(int gain) {
		bandView.setMax(gain);
	}

	public int getGain() {
		return bandView.getProgress();
	}

	public void setGain(int gain) {
		bandView.setProgress(gain);
		gainView.setText(getGainStr(gain));
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;

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
	public void onStopTrackingTouch() {
		if (onBandChangedListener != null) {
			onBandChangedListener.onBandStopTracking();
		}
	}

	@Override
	public void onProgressChanged(int progress) {
		gainView.setText(getGainStr(progress));
		if (onBandChangedListener != null) {
			onBandChangedListener.onBandChanged(this);
		}
	}

	private String getGainStr(int gain) {
		int gainStringFormatId = gain > 0
				? R.string.equalizer_format_gain_positive
				: R.string.equalizer_format_gain;

		return getResources().getString(gainStringFormatId, gain);
	}
}