package com.edavtyan.materialplayer.ui.lists.artist_list;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.lastfm.LastfmApi;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.lists.lib.ListModel;

import java.util.List;

public class ArtistListModel extends ListModel {

	private final ArtistDB db;
	private final TrackDB trackDB;
	private final LastfmApi lastfmApi;
	private List<Artist> artists;

	public ArtistListModel(
			ModelServiceModule serviceModule,
			ArtistDB db,
			TrackDB trackDB,
			LastfmApi lastfmApi) {
		super(serviceModule);
		this.db = db;
		this.trackDB = trackDB;
		this.lastfmApi = lastfmApi;
	}

	public void update() {
		artists = queryArtists();
	}

	public void addToPlaylist(int artistId) {
		getService().getPlayer().addManyTracks(trackDB.getTracksWithArtistId(artistId));
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
		return trackDB.getTracksWithArtistId(getArtistAtIndex(position).getId());
	}

	public String getArtistImageUrl(int position) {
		return lastfmApi.getArtistInfo(artists.get(position).getTitle()).getLargeImageUrl();
	}

	protected List<Artist> queryArtists() {
		return db.getAllArtists();
	}
}
