package com.edavtyan.materialplayer.components.search.tracks;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.search.base.SearchAdapter;
import com.edavtyan.materialplayer.db.Track;

public class SearchTrackAdapter extends SearchAdapter<SearchTrackViewHolder, Track> {
	public SearchTrackAdapter(Context context) {
		super(context);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.listitem_track_compact;
	}

	@Override
	public SearchTrackViewHolder onCreateViewHolder(View itemView) {
		return new SearchTrackViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(SearchTrackViewHolder holder, int position) {
		holder.setTitle(getData().get(position).getTitle());
	}
}
