package com.edavtyan.materialplayer.components.audioeffects.views;

import android.content.Context;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;

import lombok.Setter;

public class TitledSeekbar
		extends LinearLayout
		implements AppCompatSeekBar.OnSeekBarChangeListener {

	private AppCompatSeekBar seekbar;

	private @Setter OnProgressChangedListener onProgressChangedListener;

	public interface OnProgressChangedListener {
		void onProgressChange(int seekbarId, int progress);
		void onStopTrackingTouch(int seekbarId);
	}

	public TitledSeekbar(Context context, AttributeSet attrs) {
		super(context, attrs);

		View root = LayoutInflater
				.from(context)
				.inflate(R.layout.partial_titled_seekbar, this, true);
		TitledSeekbarAttributes attributes = new TitledSeekbarAttributes(context, attrs);

		seekbar = (AppCompatSeekBar) root.findViewById(R.id.seekbar);
		seekbar.setMax(attributes.getMax());
		seekbar.setProgress(attributes.getProgress());
		seekbar.setOnSeekBarChangeListener(this);

		TextView title = (TextView) root.findViewById(R.id.title);
		title.setText(attributes.getText());
		title.setTextSize(TypedValue.COMPLEX_UNIT_PX, attributes.getTextSize());
		title.getLayoutParams().width = attributes.getTextWidth();
	}

	public void setProgress(int progress) {
		seekbar.setProgress(progress);
	}

	public void setMax(int max) {
		seekbar.setMax(max);
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
