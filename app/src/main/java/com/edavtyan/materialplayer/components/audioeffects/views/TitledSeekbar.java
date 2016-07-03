package com.edavtyan.materialplayer.components.audioeffects.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;

import lombok.Setter;

public class TitledSeekbar extends LinearLayout implements SeekBar.OnSeekBarChangeListener {
	private SeekBar seekbar;
	private TextView title;

	private @Setter OnProgressChangedListener onProgressChangedListener;

	/* Events */

	public interface OnProgressChangedListener {
		void onProgressChange(int seekbarId, int progress);
		void onStopTrackingTouch(int seekbarId);
	}

	/* Constructors */

	public TitledSeekbar(Context context, AttributeSet attrs) {
		super(context, attrs);

		View root = LayoutInflater
				.from(context)
				.inflate(R.layout.layout_titled_seekbar, this, true);
		seekbar = (SeekBar) root.findViewById(R.id.seekbar);
		seekbar.setOnSeekBarChangeListener(this);
		title = (TextView) root.findViewById(R.id.title);

		TypedArray typedAttrs = null;
		try {
			typedAttrs = context.obtainStyledAttributes(attrs, R.styleable.TitledSeekbar);
			seekbar.setProgress(typedAttrs.getInteger(R.styleable.TitledSeekbar_ts_progress, 0));
			seekbar.setMax(typedAttrs.getInteger(R.styleable.TitledSeekbar_ts_max, 0));
			title.setText(typedAttrs.getString(R.styleable.TitledSeekbar_ts_title));
			title.getLayoutParams().width = typedAttrs.getDimensionPixelSize(
					R.styleable.TitledSeekbar_ts_width,
					LayoutParams.WRAP_CONTENT);
		} finally {
			if (typedAttrs != null) typedAttrs.recycle();
		}
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
	public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
		if (onProgressChangedListener != null) {
			onProgressChangedListener.onProgressChange(seekBar.getId(), progress);
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		if (onProgressChangedListener != null) {
			onProgressChangedListener.onStopTrackingTouch(seekBar.getId());
		}
	}
}
