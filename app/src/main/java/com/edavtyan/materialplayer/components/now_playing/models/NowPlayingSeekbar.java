package com.edavtyan.materialplayer.components.now_playing.models;

import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;
import com.edavtyan.materialplayer.utils.DurationUtils;

public class NowPlayingSeekbar
		implements NowPlayingMvp.View.Seekbar,
				   SeekBar.OnSeekBarChangeListener {

	private final SeekBar seekbar;
	private final TextView currentTimeView;
	private final TextView totalTimeView;
	private final NowPlayingMvp.Presenter presenter;

	public NowPlayingSeekbar(NowPlayingActivity activity, NowPlayingMvp.Presenter presenter) {
		this.presenter = presenter;
		seekbar = activity.findView(R.id.seekbar);
		seekbar.setOnSeekBarChangeListener(this);
		currentTimeView = activity.findView(R.id.timeCurrent);
		totalTimeView = activity.findView(R.id.timeTotal);
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
