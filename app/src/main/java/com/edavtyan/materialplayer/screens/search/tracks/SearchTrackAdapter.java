package com.edavtyan.materialplayer.screens.search.tracks;

import android.content.Context;

import com.edavtyan.materialplayer.screens.SdkFactory;
import com.edavtyan.materialplayer.screens.lists.track_list.TrackListAdapter;

public class SearchTrackAdapter extends TrackListAdapter {
	public SearchTrackAdapter(Context context, SearchTrackPresenter presenter, SdkFactory sdkFactory) {
		super(context, presenter, sdkFactory);
	}
}
