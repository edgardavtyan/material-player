package com.edavtyan.materialplayer.components.artist_detail;

import android.content.Context;
import android.graphics.Bitmap;

import com.edavtyan.materialplayer.components.album_all.AlbumListModel;
import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.mvp.list.CompactListPref;

import java.util.List;

public class ArtistDetailModel extends AlbumListModel implements ArtistDetailMvp.Model {
	private final ArtistDB artistDB;
	private final AlbumDB albumDB;
	private final String artistTitle;
	private final ArtistDetailImageLoader artistDetailImageLoader;

	public ArtistDetailModel(
			Context context,
			ArtistDB artistDB,
			AlbumDB albumDB,
			TrackDB trackDB,
			CompactListPref compactListPref,
			ArtistDetailImageLoader artistDetailImageLoader,
			String artistTitle) {
		super(context, albumDB, trackDB, compactListPref);
		this.artistDB = artistDB;
		this.albumDB = albumDB;
		this.artistTitle = artistTitle;
		this.artistDetailImageLoader = artistDetailImageLoader;
	}

	@Override
	protected List<Album> queryAlbums() {
		return albumDB.getAlbumsWithArtistTitle(artistTitle);
	}

	public Artist getArtist() {
		return artistDB.getArtistWithTitle(artistTitle);
	}

	@Override
	public Bitmap getLocalArtistImage() {
		return artistDetailImageLoader.getArtFromLocalStorage(artistTitle);
	}

	public void loadArtistImageFromApi(ArtistImageTask.OnArtLoadedCallback callback) {
		new ArtistImageTask(artistDetailImageLoader, callback).execute(artistTitle);
	}
}
