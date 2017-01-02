package com.edavtyan.materialplayer.components.audioeffects.views;

import android.content.Context;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.widget.SeekBar;

import com.edavtyan.materialplayer.R;
import com.edavtyan.utils.generic.GenericFrameLayout;

import lombok.Setter;

public class DoubleSeekbar extends GenericFrameLayout implements SeekBar.OnSeekBarChangeListener {
	private final AppCompatSeekBar seekbar;

	private @Setter OnStartTrackingTouchListener onStartTrackingTouchListener;
	private @Setter OnStopTrackingTouchListener onStopTrackingTouchListener;
	private @Setter OnProgressChangedListener onProgressChangedListener;

	interface OnStartTrackingTouchListener {
		void onStartTrackingTouch(DoubleSeekbar seekbar);
	}

	interface OnStopTrackingTouchListener {
		void onStopTrackingTouch();
	}

	interface OnProgressChangedListener {
		void onProgressChanged(int progress);
	}

	public DoubleSeekbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflate(context, R.layout.view_double_seekbar, this);

		seekbar = findView(R.id.seekbar);
		seekbar.setOnSeekBarChangeListener(this);
	}

	public int getMax() {
		return seekbar.getMax() / 2;
	}

	public void setMax(int max) {
		seekbar.setMax(max * 2);
	}

	public int getProgress() {
		return seekbar.getProgress() - getMax();
	}

	public void setProgress(int progress) {
		seekbar.setProgress(progress + getMax());
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (onProgressChangedListener != null) {
			onProgressChangedListener.onProgressChanged(getProgress());
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		if (onStartTrackingTouchListener != null) {
			onStartTrackingTouchListener.onStartTrackingTouch(this);
		}
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		if (onStopTrackingTouchListener != null) {
			onStopTrackingTouchListener.onStopTrackingTouch();
		}
	}
}
