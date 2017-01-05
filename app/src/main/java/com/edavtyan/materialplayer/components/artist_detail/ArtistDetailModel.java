package com.edavtyan.materialplayer.components.artist_detail;

import android.content.Context;

import com.edavtyan.materialplayer.components.album_all.AlbumListModel;
import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.mvp.list.CompactListPref;
import com.edavtyan.materialplayer.utils.ArtistArtProvider;

import java.util.List;

public class ArtistDetailModel extends AlbumListModel implements ArtistDetailMvp.Model {
	private final ArtistDB artistDB;
	private final AlbumDB albumDB;
	private final String artistTitle;
	private final ArtistArtProvider artistArtProvider;

	public ArtistDetailModel(
			Context context,
			ArtistDB artistDB,
			AlbumDB albumDB,
			TrackDB trackDB,
			CompactListPref compactListPref,
			ArtistArtProvider artistArtProvider,
			String artistTitle) {
		super(context, albumDB, trackDB, compactListPref);
		this.artistDB = artistDB;
		this.albumDB = albumDB;
		this.artistTitle = artistTitle;
		this.artistArtProvider = artistArtProvider;
	}

	@Override
	protected List<Album> queryAlbums() {
		return albumDB.getAlbumsWithArtistTitle(artistTitle);
	}

	public Artist getArtist() {
		return artistDB.getArtistWithTitle(artistTitle);
	}

	public void loadArtistImage(ArtistImageTask.OnArtLoadedCallback callback) {
		new ArtistImageTask(artistArtProvider, callback).execute(artistTitle);
	}
}
