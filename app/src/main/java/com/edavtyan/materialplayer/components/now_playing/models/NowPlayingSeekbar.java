package com.edavtyan.materialplayer.components.now_playing.models;

import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingPresenter;
import com.ed.libsutils.DurationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NowPlayingSeekbar
		implements SeekBar.OnSeekBarChangeListener {

	@BindView(R.id.seekbar) SeekBar seekbar;
	@BindView(R.id.time_current) TextView currentTimeView;
	@BindView(R.id.time_total) TextView totalTimeView;

	private final NowPlayingPresenter presenter;

	public NowPlayingSeekbar(NowPlayingActivity activity, NowPlayingPresenter presenter) {
		ButterKnife.bind(this, activity);
		this.presenter = presenter;
		seekbar.setOnSeekBarChangeListener(this);
	}

	public void setPosition(int timeMS) {
		seekbar.setProgress(timeMS);
	}

	public void setPositionText(int timeMS) {
		currentTimeView.setText(DurationUtils.toStringUntilHours(timeMS));
	}

	public void setDuration(int durationMS) {
		seekbar.setMax(durationMS);
	}

	public void setDurationText(int durationMS) {
		totalTimeView.setText(DurationUtils.toStringUntilHours(durationMS));
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (fromUser) presenter.onSeekChanged(progress);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		presenter.onSeekStop(seekBar.getProgress());
	}
}
