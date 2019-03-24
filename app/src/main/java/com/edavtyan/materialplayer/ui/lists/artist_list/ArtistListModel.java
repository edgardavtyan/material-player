package com.edavtyan.materialplayer.ui.lists.artist_list;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.MediaDB;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.lists.lib.ListModel;

import java.util.List;

public class ArtistListModel extends ListModel {

	private final MediaDB mediaDB;
	private final ArtistListImageLoader imageLoader;
	private List<Artist> artists;

	public ArtistListModel(
			ModelServiceModule serviceModule,
			MediaDB mediaDB,
			ArtistListImageLoader imageLoader) {
		super(serviceModule);
		this.mediaDB = mediaDB;
		this.imageLoader = imageLoader;
	}

	public void update() {
		artists = queryArtists();
	}

	public void addToPlaylist(int artistId) {
		getService().getPlayer().addManyTracks(mediaDB.getTracksWithArtistId(artistId));
	}

	public int getArtistCount() {
		if (artists == null) return 0;
		return artists.size();
	}

	public Artist getArtistAtIndex(int position) {
		if (artists == null) {
			throw new IllegalStateException("Artists have not been initialized");
		}

		return artists.get(position);
	}

	public List<Track> getArtistTracks(int position) {
		return mediaDB.getTracksWithArtistId(getArtistAtIndex(position).getId());
	}

	public void getArtistImageLink(int position, ArtistListImageTask.Callback callback) {
		String artistTitle = artists.get(position).getTitle();
		if (imageLoader.isCached(artistTitle)) {
			callback.onArtLoaded(imageLoader.getLinkFromCache(artistTitle));
		}

		new ArtistListImageTask(imageLoader, callback).execute(artistTitle);
	}

	protected List<Artist> queryArtists() {
		return mediaDB.getAllArtists();
	}
}
