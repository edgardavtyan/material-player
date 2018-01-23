package com.edavtyan.materialplayer.screens.detail.album_detail;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.screens.SdkFactory;
import com.edavtyan.materialplayer.screens.lists.track_list.TrackListAdapter;
import com.edavtyan.materialplayer.screens.lists.track_list.TrackListPresenter;
import com.edavtyan.materialplayer.screens.lists.track_list.TrackListViewHolder;

public class AlbumDetailAdapter extends TrackListAdapter {
	private final TrackListPresenter presenter;
	private final SdkFactory sdkFactory;

	public AlbumDetailAdapter(Context context, AlbumDetailPresenter presenter, SdkFactory sdkFactory) {
		super(context, presenter, sdkFactory);
		this.presenter = presenter;
		this.sdkFactory = sdkFactory;
	}

	@Override
	public TrackListViewHolder onCreateViewHolder(Context context, View view) {
		return new AlbumDetailViewHolder(context, view, presenter, sdkFactory);
	}
}
