package com.edavtyan.materialplayer.screens.detail.artist_detail;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.screens.lists.album_list.AlbumListAdapter;
import com.edavtyan.materialplayer.screens.lists.album_list.AlbumListViewHolder;

public class ArtistDetailAdapter extends AlbumListAdapter {
	private final ArtistDetailPresenter presenter;

	public ArtistDetailAdapter(Context context, ArtistDetailPresenter presenter) {
		super(context, presenter);
		this.presenter = presenter;
	}

	@Override
	public AlbumListViewHolder onCreateViewHolder(Context context, View view) {
		return new ArtistDetailViewHolder(context, view, presenter);
	}
}
