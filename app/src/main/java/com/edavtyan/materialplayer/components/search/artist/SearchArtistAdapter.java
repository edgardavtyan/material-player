package com.edavtyan.materialplayer.components.search.artist;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.search.base.SearchAdapter;

public class SearchArtistAdapter extends SearchAdapter<SearchArtistViewHolder> {
	public SearchArtistAdapter(Context context, SearchArtistPresenter presenter) {
		super(context, presenter);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.listitem_track_compact;
	}

	@Override
	public SearchArtistViewHolder onCreateViewHolder(View itemView) {
		return new SearchArtistViewHolder(itemView);
	}
}
