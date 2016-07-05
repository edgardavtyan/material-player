package com.edavtyan.materialplayer.components.albums.views.detail;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.components.tracks.TrackDB;
import com.edavtyan.materialplayer.components.tracks.list.TracksAdapter;

public class AlbumDetailAdapter extends TracksAdapter<AlbumTracksViewHolder> {
	public AlbumDetailAdapter(Context context, TrackDB trackDB) {
		super(context, trackDB);
	}

	@Override
	public AlbumTracksViewHolder createViewHolder(View view) {
		return new AlbumTracksViewHolder(context, view);
	}
}
