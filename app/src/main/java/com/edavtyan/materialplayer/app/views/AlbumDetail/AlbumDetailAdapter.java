package com.edavtyan.materialplayer.app.views.albumdetail;

import android.content.Context;
import android.database.Cursor;

import com.edavtyan.materialplayer.app.utils.DurationUtils;
import com.edavtyan.materialplayer.app.views.trackslist.TracksListAdapter;

public class AlbumDetailAdapter extends TracksListAdapter {
	public AlbumDetailAdapter(Context context, Cursor cursor) {
		super(context, cursor);
	}

	@Override
	public String getTrackInfo() {
		return DurationUtils.toStringUntilHours(tracksProvider.getDuration(cursor));
	}
}
