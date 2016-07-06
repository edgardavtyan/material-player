package com.edavtyan.materialplayer.components.audioeffects.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;

import lombok.Getter;
import lombok.Setter;

public class TitledSeekbar
		extends LinearLayout
		implements AppCompatSeekBar.OnSeekBarChangeListener {
	private AppCompatSeekBar seekbar;
	private TextView title;

	private @Setter OnProgressChangedListener onProgressChangedListener;

	/* Events */

	public interface OnProgressChangedListener {
		void onProgressChange(int seekbarId, int progress);
		void onStopTrackingTouch(int seekbarId);
	}

	//---

	private class Attributes {
		private static final int DEFAULT_TEXT_SIZE = 25;
		private static final int DEFAULT_PROGRESS = 30;
		private static final int DEFAULT_MAX = 100;
		private static final String DEFAULT_TEXT = "Title";

		private @Getter String text;
		private @Getter int textSize;
		private @Getter int textWidth;
		private @Getter int progress;
		private @Getter int max;

		public Attributes(Context context, AttributeSet attributeSet) {
			TypedArray attrs = null;
			try {
				attrs = context.obtainStyledAttributes(attributeSet, R.styleable.TitledSeekbar);
				text = attrs.getString(R.styleable.TitledSeekbar_ts_text);
				if (text == null) text = DEFAULT_TEXT;
				textSize = attrs.getDimensionPixelSize(
						R.styleable.TitledSeekbar_ts_textSize, DEFAULT_TEXT_SIZE);
				textWidth = attrs.getDimensionPixelSize(
						R.styleable.TitledSeekbar_ts_textWidth, LayoutParams.WRAP_CONTENT);
				progress = attrs.getInteger(
						R.styleable.TitledSeekbar_ts_progress, DEFAULT_PROGRESS);
				max = attrs.getInteger(
						R.styleable.TitledSeekbar_ts_max, DEFAULT_MAX);
			} finally {
				if (attrs != null) attrs.recycle();
			}
		}
	}

	/* Constructors */

	public TitledSeekbar(Context context, AttributeSet attrs) {
		super(context, attrs);

		View root = LayoutInflater.from(context).inflate(R.layout.partial_titled_seekbar, this, true);
		Attributes attributes = new Attributes(context, attrs);

		seekbar = (AppCompatSeekBar) root.findViewById(R.id.seekbar);
		seekbar.setMax(attributes.getMax());
		seekbar.setProgress(attributes.getProgress());
		seekbar.setOnSeekBarChangeListener(this);

		title = (TextView) root.findViewById(R.id.title);
		title.setText(attributes.getText());
		title.setTextSize(TypedValue.COMPLEX_UNIT_PX, attributes.getTextSize());
		title.getLayoutParams().width = attributes.getTextWidth();
	}

	/* Public methods */

	public void setProgress(int progress) {
		seekbar.setProgress(progress);
	}

	public void setMax(int max) {
		seekbar.setMax(max);
	}

	/* SeekBar.OnSeekBarChangeListener */

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (onProgressChangedListener != null && fromUser) {
			onProgressChangedListener.onProgressChange(getId(), progress);
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		if (onProgressChangedListener != null) {
			onProgressChangedListener.onStopTrackingTouch(getId());
		}
	}
}
