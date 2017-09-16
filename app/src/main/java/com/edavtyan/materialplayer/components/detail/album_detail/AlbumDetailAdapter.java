package com.edavtyan.materialplayer.components.detail.album_detail;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.components.lists.track_list.TrackListAdapter;
import com.edavtyan.materialplayer.components.lists.track_list.TrackListMvp;
import com.edavtyan.materialplayer.components.lists.track_list.TrackListViewHolder;

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
