package com.edavtyan.materialplayer.app.views.albumdetail;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.app.utils.DurationUtils;
import com.edavtyan.materialplayer.app.views.trackslist.TracksViewHolder;

public class AlbumDetailViewHolder extends TracksViewHolder {
	public AlbumDetailViewHolder(Context context, View itemView) {
		super(context, itemView);
	}

	@Override
	public void setInfo(long duration, String artist, String album) {
		String info = DurationUtils.toStringUntilHours(duration);
		infoView.setText(info);
	}
}
