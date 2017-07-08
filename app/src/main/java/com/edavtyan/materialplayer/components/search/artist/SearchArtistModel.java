package com.edavtyan.materialplayer.components.search.artist;

import android.content.Context;

import com.edavtyan.materialplayer.components.artist_all.ArtistListImageLoader;
import com.edavtyan.materialplayer.components.artist_all.ArtistListModel;
import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.lib.mvp.list.CompactListPref;

import java.util.List;

import lombok.Setter;

public class SearchArtistModel extends ArtistListModel {
	private final ArtistDB artistDB;

	private @Setter String artistTitle;

	public SearchArtistModel(
			Context context,
			ArtistDB artistDB,
			ArtistListImageLoader imageLoader,
			CompactListPref compactListPref) {
		super(context, artistDB, imageLoader, compactListPref);
		this.artistDB = artistDB;
	}

	@Override
	protected List<Artist> queryArtists() {
		return artistDB.searchArtists(artistTitle);
	}
}
