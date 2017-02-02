package com.edavtyan.materialplayer.components.artist_detail;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.components.album_all.AlbumListAdapter;
import com.edavtyan.materialplayer.components.album_all.AlbumListViewHolder;

public class ArtistDetailAdapter extends AlbumListAdapter {
	private final ArtistDetailMvp.Presenter presenter;

	public ArtistDetailAdapter(Context context, ArtistDetailMvp.Presenter presenter) {
		super(context, presenter);
		this.presenter = presenter;
	}

	@Override
	public AlbumListViewHolder onCreateViewHolder(Context context, View view) {
		return new ArtistDetailViewHolder(context, view, presenter);
	}
}
