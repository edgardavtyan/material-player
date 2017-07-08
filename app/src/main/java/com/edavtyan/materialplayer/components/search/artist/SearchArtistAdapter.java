package com.edavtyan.materialplayer.components.search.artist;

import android.content.Context;

import com.edavtyan.materialplayer.components.artist_all.ArtistListAdapter;
import com.edavtyan.materialplayer.components.artist_all.ArtistListMvp;

public class SearchArtistAdapter extends ArtistListAdapter {
	public SearchArtistAdapter(Context context, ArtistListMvp.Presenter presenter) {
		super(context, presenter);
	}
}
