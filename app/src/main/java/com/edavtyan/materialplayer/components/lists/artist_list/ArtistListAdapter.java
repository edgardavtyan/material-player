package com.edavtyan.materialplayer.components.lists.artist_list;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.lists.lib.ListAdapter;

public class ArtistListAdapter extends ListAdapter<ArtistListViewHolder> {

	private final ArtistListMvp.Presenter presenter;

	public ArtistListAdapter(Context context, ArtistListMvp.Presenter presenter) {
		super(context, presenter);
		this.presenter = presenter;
	}

	@Override
	public int getNormalLayoutId() {
		return R.layout.listitem_album;
	}

	@Override
	public int getCompactLayoutId() {
		return R.layout.listitem_album_compact;
	}

	@Override
	public ArtistListViewHolder onCreateViewHolder(Context context, View view) {
		return new ArtistListViewHolder(context, view, presenter);
	}
}
