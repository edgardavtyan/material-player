package com.edavtyan.materialplayer.components.audioeffects.views;

import android.content.Context;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.edavtyan.materialplayer.R;

import lombok.Setter;

public class DoubleSeekbar extends FrameLayout implements SeekBar.OnSeekBarChangeListener {
	private AppCompatSeekBar seekbar;

	private @Setter OnStartTrackingTouchListener onStartTrackingTouchListener;
	private @Setter OnStopTrackingTouchListener onStopTrackingTouchListener;
	private @Setter OnProgressChangedListener onProgressChangedListener;

	interface OnStartTrackingTouchListener {
		void onStartTrackingTouch(DoubleSeekbar seekbar);
	}

	interface OnStopTrackingTouchListener {
		void onStopTrackingTouch(DoubleSeekbar seekbar);
	}

	interface OnProgressChangedListener {
		void onProgressChanged(DoubleSeekbar seekbar, int progress, boolean fromUser);
	}

	public DoubleSeekbar(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater.from(context).inflate(R.layout.view_double_seekbar, this, true);

		seekbar = (AppCompatSeekBar) findViewById(R.id.seekbar);
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
			onProgressChangedListener.onProgressChanged(this, getProgress(), fromUser);
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
			onStopTrackingTouchListener.onStopTrackingTouch(this);
		}
	}
}
