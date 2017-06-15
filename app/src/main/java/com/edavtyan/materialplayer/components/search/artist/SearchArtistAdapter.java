package com.edavtyan.materialplayer.components.search.artist;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.search.base.SearchAdapter;
import com.edavtyan.materialplayer.db.Artist;

public class SearchArtistAdapter extends SearchAdapter<SearchArtistViewHolder, Artist> {
	public SearchArtistAdapter(Context context) {
		super(context);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.listitem_track_compact;
	}

	@Override
	public SearchArtistViewHolder onCreateViewHolder(View itemView) {
		return new SearchArtistViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(SearchArtistViewHolder holder, int position) {
		holder.setTitle(getData().get(position).getTitle());
	}
}
