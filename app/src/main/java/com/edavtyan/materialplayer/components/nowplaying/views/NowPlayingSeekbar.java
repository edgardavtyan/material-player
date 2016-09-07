package com.edavtyan.materialplayer.components.nowplaying.views;

import android.widget.SeekBar;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.nowplaying.NowPlayingPresenter;
import com.edavtyan.materialplayer.lib.base.BaseActivity;

public class NowPlayingSeekbar implements SeekBar.OnSeekBarChangeListener {
	private final SeekBar seekbar;
	private final TextView currentTimeView;
	private final TextView totalTimeView;
	private final NowPlayingPresenter presenter;

	public NowPlayingSeekbar(BaseActivity activity, NowPlayingPresenter presenter) {
		this.presenter = presenter;
		seekbar = activity.findView(R.id.seekbar);
		seekbar.setOnSeekBarChangeListener(this);
		currentTimeView = activity.findView(R.id.timeCurrent);
		totalTimeView = activity.findView(R.id.timeTotal);
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
