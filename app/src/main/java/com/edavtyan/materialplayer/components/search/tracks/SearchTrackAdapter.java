package com.edavtyan.materialplayer.components.search.tracks;

import android.content.Context;
import android.view.View;

import com.edavtyan.materialplayer.R;
import com.edavtyan.materialplayer.components.search.base.SearchAdapter;
import com.edavtyan.materialplayer.components.track_all.TrackListAdapter;

public class SearchTrackAdapter extends TrackListAdapter {
	public SearchTrackAdapter(Context context, SearchTrackPresenter presenter) {
		super(context, presenter);
	}
}
