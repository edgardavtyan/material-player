package com.edavtyan.materialplayer.views.albumdetail;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

import com.edavtyan.materialplayer.views.trackslist.TracksAdapter;

public class AlbumDetailAdapter extends TracksAdapter<AlbumTracksViewHolder> {
	public AlbumDetailAdapter(Context context, Cursor cursor) {
		super(context, cursor);
	}

	@Override
	public AlbumTracksViewHolder createViewHolder(View view) {
		return new AlbumTracksViewHolder(context, view);
	}
}
