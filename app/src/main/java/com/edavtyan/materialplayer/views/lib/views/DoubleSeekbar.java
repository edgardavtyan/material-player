package com.edavtyan.materialplayer.views.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

import app.minimize.com.seek_bar_compat.SeekBarCompat;
import lombok.Setter;

public class DoubleSeekbar extends SeekBarCompat implements SeekBar.OnSeekBarChangeListener {
	/*
	 * Fields
	 */

	private int max = 100;
	private @Setter SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;

	/*
	 * Constructors
	 */

	public DoubleSeekbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		super.setOnSeekBarChangeListener(this);
	}

	/*
	 * Public methods
	 */

	@Override
	public void setMax(int max) {
		super.setMax(max * 2);
		this.max = max;
	}

	@Override
	public synchronized void setProgress(int progress) {
		super.setProgress(progress + max);
	}

	/*
	 * SeekBar.OnSeekBarChangeListener
	 */

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (onSeekBarChangeListener != null) {
			onSeekBarChangeListener.onProgressChanged(seekBar, progress - max, fromUser);
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		if (onSeekBarChangeListener != null) {
			onSeekBarChangeListener.onStartTrackingTouch(seekBar);
		}
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		if (onSeekBarChangeListener != null) {
			onSeekBarChangeListener.onStopTrackingTouch(seekBar);
		}
	}
}
