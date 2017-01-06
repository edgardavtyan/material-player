package com.edavtyan.materialplayer.components.artist_all;

import android.content.Context;
import android.graphics.Bitmap;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.lib.mvp.list.CompactListPref;
import com.edavtyan.materialplayer.lib.mvp.list.ListModel;

import java.util.List;

public class ArtistListModel
		extends ListModel
		implements ArtistListMvp.Model {

	private final ArtistDB db;
	private final ArtistListImageLoader imageLoader;
	private List<Artist> artists;

	public ArtistListModel(
			Context context,
			ArtistDB db,
			ArtistListImageLoader imageLoader,
			CompactListPref compactListPref) {
		super(context, compactListPref);
		this.db = db;
		this.imageLoader = imageLoader;
	}

	@Override
	public void update() {
		artists = db.getAllArtists();
	}

	@Override
	public int getArtistCount() {
		if (artists == null) return 0;
		return artists.size();
	}

	@Override
	public Artist getArtistAtIndex(int position) {
		if (artists == null) return null;
		return artists.get(position);
	}

	@Override
	public void getArtistImage(int position, ArtistListImageTask.Callback callback) {
		String artistTitle = artists.get(position).getTitle();
		Bitmap imageFromCache = imageLoader.getImageFromMemoryCache(artistTitle);
		if (imageFromCache != null) {
			callback.onArtLoaded(imageFromCache);
		} else {
			new ArtistListImageTask(imageLoader, callback).execute(artistTitle);
		}
	}
}
