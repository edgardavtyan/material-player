package com.edavtyan.materialplayer.components.now_playing.models;

import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingActivity;
import com.edavtyan.materialplayer.components.now_playing.NowPlayingMvp;

public class NowPlayingInfo implements NowPlayingMvp.View.Info {
	private final NowPlayingActivity activity;
	private final TextView titleView;
	private final TextView infoView;

	public NowPlayingInfo(NowPlayingActivity activity) {
		this.activity = activity;

		titleView = activity.findView(R.id.title);
		infoView = activity.findView(R.id.info);
	}

	@Override
	public void setTitle(String title) {
		titleView.setText(title);
	}

	@Override
	public void setInfo(String artist, String album) {
		String info = activity.getResources().getString(R.string.nowplaying_info_pattern, artist, album);
		infoView.setText(info);
	}
}
