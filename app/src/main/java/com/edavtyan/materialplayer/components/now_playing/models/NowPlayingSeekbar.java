package com.edavtyan.materialplayer.components.now_playing.models;

import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;
import com.edavtyan.materialplayer.utils.DurationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NowPlayingSeekbar
		implements NowPlayingMvp.View.Seekbar,
				   SeekBar.OnSeekBarChangeListener {

	@BindView(R.id.seekbar) SeekBar seekbar;
	@BindView(R.id.time_current) TextView currentTimeView;
	@BindView(R.id.time_total) TextView totalTimeView;

	private final NowPlayingMvp.Presenter presenter;

	public NowPlayingSeekbar(NowPlayingActivity activity, NowPlayingMvp.Presenter presenter) {
		ButterKnife.bind(this, activity);
		this.presenter = presenter;
		seekbar.setOnSeekBarChangeListener(this);
	}

	@Override
	public void setPosition(int timeMS) {
		seekbar.setProgress(timeMS);
	}

	@Override
	public void setPositionText(int timeMS) {
		currentTimeView.setText(DurationUtils.toStringUntilHours(timeMS));
	}

	@Override
	public void setDuration(int durationMS) {
		seekbar.setMax(durationMS);
	}

	@Override
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
