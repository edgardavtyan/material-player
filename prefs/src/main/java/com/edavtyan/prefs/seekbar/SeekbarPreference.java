package com.edavtyan.prefs.seekbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.prefs.R;
import com.edavtyan.prefs.R2;
import com.edavtyan.prefs.base.BasePreference;

import app.minimize.com.seek_bar_compat.SeekBarCompat;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SeekbarPreference extends BasePreference {
	@BindView(R2.id.title) TextView titleView;
	@BindView(R2.id.seekbar) SeekBarCompat seekbarView;
	@BindView(R2.id.value_text) TextView valueTextView;

	private SeekbarPreferencePresenter presenter;

	private SeekBar.OnSeekBarChangeListener onSeekbarChangeListener = new SeekBar.OnSeekBarChangeListener() {
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			presenter.onSeek(progress);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}
	};

	public SeekbarPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPref(attrs);
	}

	public SeekbarPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPref(attrs);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public int getSeek() {
		return seekbarView.getProgress();
	}

	public void setSeek(int seek) {
		seekbarView.setProgress(seek);
	}

	public void setSeekText(String text) {
		valueTextView.setText(text);
	}

	public void setMaxSeek(int maxValue) {
		seekbarView.setMax(maxValue);
	}

	public void setColor(int color) {
		seekbarView.setProgressColor(color);
		seekbarView.setThumbColor(color);
	}

	private void initPref(AttributeSet attrs) {
		LayoutInflater.from(context).inflate(R.layout.entry_seekbar, this, true);
		ButterKnife.bind(this);

		setOrientation(VERTICAL);
		setPadding(0, 0, 0, 0);
		seekbarView.setOnSeekBarChangeListener(onSeekbarChangeListener);

		SeekbarPreferenceModel model = new SeekbarPreferenceModel(context, attrs);

		if (isInEditMode()) {
			setTitle(model.getTitle());
			setSeek(model.getDefaultValue());
			setMaxSeek(model.getMaxValue());
			setSeekText(String.format(model.getFormat(), getSeek() * model.getMultiplier()));
			return;
		}

		presenter = new SeekbarPreferencePresenter(this, model);
		presenter.onInit();
	}
}
