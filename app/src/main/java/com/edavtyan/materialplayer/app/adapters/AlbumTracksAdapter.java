package com.edavtyan.materialplayer.app.adapters;

import android.content.Context;
import android.database.Cursor;

import com.edavtyan.materialplayer.app.models.columns.TrackColumns;
import com.edavtyan.materialplayer.app.lib.utils.DurationUtils;

public class AlbumTracksAdapter extends TracksAdapter {
	public AlbumTracksAdapter(Context context, Cursor cursor) {
		super(context, cursor);
	}

	@Override
	public String getTrackInfo() {
		return DurationUtils.toStringUntilHours(cursor.getInt(TrackColumns.DURATION));
	}
}
