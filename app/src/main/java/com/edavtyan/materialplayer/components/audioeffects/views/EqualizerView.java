package com.edavtyan.materialplayer.components.audioeffects.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import lombok.Setter;

public class EqualizerView
		extends LinearLayout
		implements EqualizerBandView.OnBandChangedListener {

	private @Setter OnBandChangedListener onBandChangedListener;

	public interface OnBandChangedListener {
		void onBandChanged(EqualizerBandView band);
		void onBandStopTracking();
	}

	public EqualizerView(Context context, AttributeSet attrs) {
		super(context, attrs);

		setOrientation(VERTICAL);
	}

	public void setBands(int count, int[] frequencies, int[] gains, int limit) {
		removeAllViews();
		for (int i = 0; i < count; i++) {
			EqualizerBandView band = new EqualizerBandView(getContext(), null);
			band.setIndex(i);
			band.setGainLimit(limit);
			band.setFrequency(frequencies[i]);
			band.setGain(gains[i]);
			band.setOnBandChangedListener(this);
			addView(band);
		}
	}

	@Override
	public void onBandStopTracking() {
		if (onBandChangedListener != null) {
			onBandChangedListener.onBandStopTracking();
		}
	}

	@Override
	public void onBandChanged(EqualizerBandView band) {
		if (onBandChangedListener != null) {
			onBandChangedListener.onBandChanged(band);
		}
	}
}
