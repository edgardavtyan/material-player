package com.edavtyan.materialplayer.components.nowplaying2;

import android.app.Activity;
import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;

public class NowPlayingSeekbarView implements SeekBar.OnSeekBarChangeListener {
	private final SeekBar seekbar;
	private final TextView currentTimeView;
	private final TextView totalTimeView;
	private final NowPlayingPresenter presenter;

	public NowPlayingSeekbarView(Activity activity, NowPlayingPresenter presenter) {
		this.presenter = presenter;
		seekbar = (SeekBar) activity.findViewById(R.id.seekbar);
		seekbar.setOnSeekBarChangeListener(this);
		currentTimeView = (TextView) activity.findViewById(R.id.timeCurrent);
		totalTimeView = (TextView) activity.findViewById(R.id.timeTotal);
	}

	public void setMax(int max) {
		seekbar.setMax(max);
	}

	public void setProgress(int progress) {
		seekbar.setProgress(progress);
	}

	public void setCurrentTime(CharSequence time) {
		currentTimeView.setText(time);
	}

	public void setTotalTime(CharSequence time) {
		totalTimeView.setText(time);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (fromUser) presenter.moveSeek(progress);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		presenter.seekTo(seekBar.getProgress());
	}
}
