package com.edavtyan.materialplayer.components.album_detail;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.components.track_all.TrackListAdapter;
import com.edavtyan.materialplayer.components.track_all.TrackListMvp;
import com.edavtyan.materialplayer.components.track_all.TrackListViewHolder;

public class AlbumDetailAdapter extends TrackListAdapter {
	private final TrackListMvp.Presenter presenter;

	public AlbumDetailAdapter(Context context, TrackListMvp.Presenter presenter) {
		super(context, presenter);
		this.presenter = presenter;
	}

	@Override
	public TrackListViewHolder onCreateViewHolder(Context context, View view) {
		return new AlbumDetailViewHolder(context, view, presenter);
	}
}
