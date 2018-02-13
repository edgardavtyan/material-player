package com.edavtyan.materialplayer.screens.lists.artist_list;

import android.graphics.Bitmap;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.screens.lists.lib.ListModel;

import java.util.List;

public class ArtistListModel extends ListModel {

	private final ArtistDB db;
	private final TrackDB trackDB;
	private final ArtistListImageLoader imageLoader;
	private List<Artist> artists;

	public ArtistListModel(
			ModelServiceModule serviceModule,
			ArtistDB db,
			TrackDB trackDB,
			ArtistListImageLoader imageLoader) {
		super(serviceModule);
		this.db = db;
		this.trackDB = trackDB;
		this.imageLoader = imageLoader;
	}

	public void update() {
		artists = queryArtists();
	}

	public void addToPlaylist(int artistId) {
		service.getPlayer().addManyTracks(trackDB.getTracksWithArtistId(artistId));
	}

	public int getArtistCount() {
		if (artists == null) return 0;
		return artists.size();
	}

	public Artist getArtistAtIndex(int position) {
		if (artists == null) return null;
		return artists.get(position);
	}

	public void getArtistImage(int position, ArtistListImageTask.Callback callback) {
		String artistTitle = artists.get(position).getTitle();
		Bitmap imageFromCache = imageLoader.getImageFromMemoryCache(artistTitle);
		if (imageFromCache != null) {
			callback.onArtLoaded(imageFromCache);
		} else {
			new ArtistListImageTask(imageLoader, callback).execute(artistTitle);
		}
	}

	protected List<Artist> queryArtists() {
		return db.getAllArtists();
	}
}
