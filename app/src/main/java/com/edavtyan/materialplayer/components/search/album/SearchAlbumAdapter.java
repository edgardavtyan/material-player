package com.edavtyan.materialplayer.components.search.album;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.search.base.SearchAdapter;
import com.edavtyan.materialplayer.db.Album;

public class SearchAlbumAdapter extends SearchAdapter<SearchAlbumViewHolder, Album> {
	public SearchAlbumAdapter(Context context) {
		super(context);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.listitem_track_compact;
	}

	@Override
	public SearchAlbumViewHolder onCreateViewHolder(View itemView) {
		return new SearchAlbumViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(SearchAlbumViewHolder holder, int position) {
		holder.setTitle(getData().get(position).getTitle());
	}
}
