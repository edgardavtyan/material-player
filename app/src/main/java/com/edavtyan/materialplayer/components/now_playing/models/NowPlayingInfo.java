package com.edavtyan.materialplayer.components.now_playing.models;

import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NowPlayingInfo implements NowPlayingMvp.View.Info {

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;

	private final NowPlayingActivity activity;

	public NowPlayingInfo(NowPlayingActivity activity) {
		this.activity = activity;
		ButterKnife.bind(this, activity);
	}

	@Override
	public void setTitle(String title) {
		titleView.setText(title);
	}

	@Override
	public void setInfo(String artist, String album) {
		String info = activity.getString(R.string.nowplaying_info_pattern, artist, album);
		infoView.setText(info);
	}
}
