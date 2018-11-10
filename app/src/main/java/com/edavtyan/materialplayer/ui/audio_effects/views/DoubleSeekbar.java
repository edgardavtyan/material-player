package com.edavtyan.materialplayer.ui.audio_effects.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.edavtyan.materialplayer.R;

import app.minimize.com.seek_bar_compat.SeekBarCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Setter;

public class DoubleSeekbar extends FrameLayout implements SeekBar.OnSeekBarChangeListener {
	@BindView(R.id.seekbar) SeekBarCompat seekbar;

	private @Setter OnStopTrackingTouchListener onStopTrackingTouchListener;
	private @Setter OnProgressChangedListener onProgressChangedListener;

	interface OnStopTrackingTouchListener {
		void onStopTrackingTouch();
	}

	interface OnProgressChangedListener {
		void onProgressChanged(int progress);
	}

	public DoubleSeekbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflate(context, R.layout.view_double_seekbar, this);
		ButterKnife.bind(this);

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
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		if (onStopTrackingTouchListener != null) {
			onStopTrackingTouchListener.onStopTrackingTouch();
		}
	}
}
