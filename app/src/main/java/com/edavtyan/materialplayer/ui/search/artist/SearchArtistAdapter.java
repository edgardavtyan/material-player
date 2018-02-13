package com.edavtyan.materialplayer.ui.search.artist;

import android.content.Context;

import com.edavtyan.materialplayer.ui.SdkFactory;
import com.edavtyan.materialplayer.ui.lists.artist_list.ArtistListAdapter;
import com.edavtyan.materialplayer.ui.lists.artist_list.ArtistListPresenter;

public class SearchArtistAdapter extends ArtistListAdapter {
	public SearchArtistAdapter(Context context, ArtistListPresenter presenter, SdkFactory sdkFactory) {
		super(context, presenter, sdkFactory);
	}
}
