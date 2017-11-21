package com.edavtyan.materialplayer.components.audio_effects.views;

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

		if (isInEditMode()) {
			int[] frequencies = new int[]{60, 230, 910, 3600, 14000};
			int[] gains = new int[]{0, 6, -9, -2, 12};
			setBands(5, frequencies, gains, 15);
		}
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
