package com.edavtyan.materialplayer.components.search.album;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.search.base.SearchAdapter;

public class SearchAlbumAdapter extends SearchAdapter<SearchAlbumViewHolder> {
	public SearchAlbumAdapter(Context context, SearchAlbumPresenter presenter) {
		super(context, presenter);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.listitem_track_compact;
	}

	@Override
	public SearchAlbumViewHolder onCreateViewHolder(View itemView) {
		return new SearchAlbumViewHolder(itemView);
	}
}
