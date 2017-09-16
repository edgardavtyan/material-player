package com.edavtyan.materialplayer.components.search.artist;

import android.content.Context;

import com.edavtyan.materialplayer.components.lists.artist_list.ArtistListAdapter;
import com.edavtyan.materialplayer.components.lists.artist_list.ArtistListMvp;

public class SearchArtistAdapter extends ArtistListAdapter {
	public SearchArtistAdapter(Context context, ArtistListMvp.Presenter presenter) {
		super(context, presenter);
	}
}
