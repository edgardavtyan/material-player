package com.edavtyan.materialplayer.app.views.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import app.minimize.com.seek_bar_compat.SeekBarCompat;
import lombok.Setter;

public class DoubleSeekbar extends FrameLayout implements SeekBar.OnSeekBarChangeListener {
	private SeekBar seekbar;
	private @Setter SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;


	public DoubleSeekbar(Context context, AttributeSet attrs) {
		super(context, attrs);

		seekbar = new SeekBarCompat(context, attrs);
		seekbar.setOnSeekBarChangeListener(this);
		setMax(seekbar.getMax());
		addView(seekbar);
	}

	/*
	 * Public methods
	 */

	public void setMax(int max) {
		seekbar.setMax(max * 2);
	}

	public int getMax() {
		return seekbar.getMax() / 2;
	}

	public void setProgress(int progress) {
		seekbar.setProgress(progress + getMax());
	}

	/*
	 * SeekBar.OnSeekBarChangeListener
	 */

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (onSeekBarChangeListener != null) {
			onSeekBarChangeListener.onProgressChanged(seekBar, progress - getMax(), fromUser);
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
