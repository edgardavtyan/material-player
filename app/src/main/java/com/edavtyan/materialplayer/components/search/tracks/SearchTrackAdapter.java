package com.edavtyan.materialplayer.components.search.tracks;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.search.base.SearchAdapter;

public class SearchTrackAdapter extends SearchAdapter<SearchTrackViewHolder> {
	public SearchTrackAdapter(Context context, SearchTrackPresenter presenter) {
		super(context, presenter);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.listitem_track_compact;
	}

	@Override
	public SearchTrackViewHolder onCreateViewHolder(View itemView) {
		return new SearchTrackViewHolder(itemView);
	}
}
