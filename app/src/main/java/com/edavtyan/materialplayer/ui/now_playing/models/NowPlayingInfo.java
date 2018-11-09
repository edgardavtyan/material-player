package com.edavtyan.materialplayer.ui.now_playing.models;

import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.theme.ThemeColors;
import com.edavtyan.materialplayer.ui.now_playing.NowPlayingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NowPlayingInfo {

	@BindView(R.id.title) TextView titleView;
	@BindView(R.id.info) TextView infoView;

	private final NowPlayingActivity activity;

	public NowPlayingInfo(NowPlayingActivity activity) {
		this.activity = activity;
		ButterKnife.bind(this, activity);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}

	public void setInfo(String artist, String album) {
		String info = activity.getString(R.string.nowplaying_info_pattern, artist, album);
		infoView.setText(info);
	}

	public void setTheme(ThemeColors colors) {
		titleView.setTextColor(colors.getTextContrastPrimary());
		infoView.setTextColor(colors.getTextContrastSecondary());
	}
}
