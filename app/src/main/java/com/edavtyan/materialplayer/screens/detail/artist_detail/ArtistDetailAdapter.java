package com.edavtyan.materialplayer.screens.detail.artist_detail;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.screens.SdkFactory;
import com.edavtyan.materialplayer.screens.lists.album_list.AlbumListAdapter;
import com.edavtyan.materialplayer.screens.lists.album_list.AlbumListViewHolder;

public class ArtistDetailAdapter extends AlbumListAdapter {
	private final ArtistDetailPresenter presenter;
	private final SdkFactory sdkFactory;

	public ArtistDetailAdapter(Context context, ArtistDetailPresenter presenter, SdkFactory sdkFactory) {
		super(context, presenter, sdkFactory);
		this.presenter = presenter;
		this.sdkFactory = sdkFactory;
	}

	@Override
	public AlbumListViewHolder onCreateViewHolder(Context context, View view) {
		return new ArtistDetailViewHolder(context, view, presenter, sdkFactory);
	}
}
