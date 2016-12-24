package com.edavtyan.materialplayer.components.album_detail;

import android.content.Context;

import com.edavtyan.materialplayer.components.track_all.TrackListAdapter;
import com.edavtyan.materialplayer.components.track_all.TrackListMvp;

public class AlbumDetailAdapter extends TrackListAdapter {
	public AlbumDetailAdapter(Context context, TrackListMvp.Presenter presenter) {
		super(context, presenter);
	}
}
