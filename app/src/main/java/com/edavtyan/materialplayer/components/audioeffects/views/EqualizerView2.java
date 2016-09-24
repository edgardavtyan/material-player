package com.edavtyan.materialplayer.components.audioeffects.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import lombok.Setter;

public class EqualizerView2
		extends LinearLayout
		implements EqualizerBandView.OnBandChangedListener {

	private @Setter OnBandChangedListener onBandChangedListener;

	public interface OnBandChangedListener {
		void onBandChanged(EqualizerBandView band);
		void onBandStopTracking(EqualizerBandView band);
	}

	public EqualizerView2(Context context, AttributeSet attrs) {
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
	public void OnBandStopTracking(EqualizerBandView band) {
		if (onBandChangedListener != null) {
			onBandChangedListener.onBandStopTracking(band);
		}
	}

	@Override
	public void onBandChanged(EqualizerBandView band, int gain) {
		if (onBandChangedListener != null) {
			onBandChangedListener.onBandChanged(band);
		}
	}
}
