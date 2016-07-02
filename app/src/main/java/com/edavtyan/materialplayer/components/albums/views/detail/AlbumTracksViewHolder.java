package com.edavtyan.materialplayer.components.albums.views.detail;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.utils.DurationUtils;
import com.edavtyan.materialplayer.components.tracks.list.TracksViewHolder;

public class AlbumTracksViewHolder extends TracksViewHolder {
	public AlbumTracksViewHolder(Context context, View itemView) {
		super(context, itemView);
	}

	@Override
	public void setInfo(long duration, String artist, String album) {
		String info = DurationUtils.toStringUntilHours(duration);
		infoView.setText(info);
	}
}
