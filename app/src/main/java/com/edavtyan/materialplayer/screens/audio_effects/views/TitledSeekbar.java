package com.edavtyan.materialplayer.screens.audio_effects.views;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Setter;

public class TitledSeekbar
		extends LinearLayout
		implements AppCompatSeekBar.OnSeekBarChangeListener {

	@BindView(R.id.seekbar) AppCompatSeekBar seekbar;
	@BindView(R.id.title) TextView titleView;

	private @Setter OnProgressChangedListener onProgressChangedListener;

	public interface OnProgressChangedListener {
		void onProgressChange(int seekbarId, int progress);
		void onStopTrackingTouch(int seekbarId);
	}

	public TitledSeekbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflate(context, R.layout.partial_titled_seekbar, this);
		ButterKnife.bind(this);

		TitledSeekbarAttributes attributes = new TitledSeekbarAttributes(context, attrs);

		seekbar.setMax(attributes.getMax());
		seekbar.setProgress(attributes.getProgress());
		seekbar.setOnSeekBarChangeListener(this);

		titleView.setText(attributes.getText());
		titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, attributes.getTextSize());
		titleView.getLayoutParams().width = attributes.getTextWidth();
	}

	public int getProgress() {
		return seekbar.getProgress();
	}

	public void setProgress(int progress) {
		seekbar.setProgress(progress);
	}

	public int getMax() {
		return seekbar.getMax();
	}

	public void setMax(int max) {
		seekbar.setMax(max);
	}

	public void setTint(int color) {
		seekbar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
		seekbar.getThumb().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (onProgressChangedListener != null && fromUser) {
			onProgressChangedListener.onProgressChange(getId(), progress);
		}
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		if (onProgressChangedListener != null) {
			onProgressChangedListener.onStopTrackingTouch(getId());
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}
}
