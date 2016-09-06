package com.edavtyan.materialplayer.components.nowplaying.views;

import android.content.Context;
import android.widget.TextView;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.lib.activities.BaseActivity;

public class NowPlayingInfo {
	private final Context context;
	private final TextView titleView;
	private final TextView infoView;

	public NowPlayingInfo(BaseActivity activity) {
		this.context = activity;
		titleView = activity.findView(R.id.title);
		infoView = activity.findView(R.id.info);
	}

	public void setTitle(CharSequence title) {
		titleView.setText(title);
	}

	public void setInfo(CharSequence artistTitle, CharSequence albumTitle) {
		String info = context.getString(R.string.nowplaying_info_pattern, artistTitle, albumTitle);
		infoView.setText(info);
	}
}
