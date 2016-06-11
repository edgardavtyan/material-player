package com.edavtyan.materialplayer.app.views.trackslist;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.app.R;
import com.edavtyan.materialplayer.app.utils.DurationUtils;

public class TracksListViewHolder extends TracksViewHolder {
	public TracksListViewHolder(Context context, View itemView) {
		super(context, itemView);
	}

	@Override
	public void setInfo(long duration, String artist, String album) {
		String info = context.getResources().getString(
				R.string.pattern_track_info, DurationUtils.toStringUntilHours(duration),
				artist, album);
		infoView.setText(info);
	}
}
