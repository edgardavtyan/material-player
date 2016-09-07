package com.edavtyan.materialplayer.components.album_mvp;

import android.content.Context;

import com.edavtyan.materialplayer.components.track_mvp.TrackListAdapter;
import com.edavtyan.materialplayer.components.track_mvp.TrackListMvp;

public class AlbumDetailAdapter extends TrackListAdapter {
	public AlbumDetailAdapter(Context context, TrackListMvp.Presenter presenter) {
		super(context, presenter);
	}
}
