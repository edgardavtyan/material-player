package com.edavtyan.materialplayer.ui.detail.artist_detail;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.lists.album_list.AlbumListModel;

import java.util.List;

public class ArtistDetailModel extends AlbumListModel {
	private final ArtistDB artistDB;
	private final AlbumDB albumDB;
	private final ArtistDetailPrefs prefs;
	private final String artistTitle;
	private final ArtistDetailImageLoader imageLoader;

	public ArtistDetailModel(
			ModelServiceModule serviceModule,
			ArtistDB artistDB,
			AlbumDB albumDB,
			TrackDB trackDB,
			ArtistDetailImageLoader imageLoader,
			AlbumArtProvider albumArtProvider,
			ArtistDetailPrefs prefs,
			String artistTitle) {
		super(serviceModule, albumDB, trackDB, albumArtProvider);
		this.artistDB = artistDB;
		this.albumDB = albumDB;
		this.prefs = prefs;
		this.artistTitle = artistTitle;
		this.imageLoader = imageLoader;
	}

	@Override
	protected List<Album> queryAlbums() {
		return albumDB.getAlbumsWithArtistTitle(artistTitle, prefs.getSort());
	}

	public Artist getArtist() {
		return artistDB.getArtistWithTitle(artistTitle);
	}

	public void loadArtistImage(ArtistDetailImageTask.OnImageLoadedCallback callback) {
		Bitmap cachedImage = imageLoader.getImageFromCache(artistTitle);
		if (cachedImage != null) {
			callback.OnImageLoaded(cachedImage);
		} else {
			new ArtistDetailImageTask(imageLoader, callback).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, artistTitle);
		}
	}

	public void sortByName() {
		albums = albumDB.getAlbumsWithArtistTitle(artistTitle, AlbumDB.KEY_TITLE);
		prefs.saveSort(AlbumDB.KEY_TITLE);
	}

	public void sortByDate() {
		albums = albumDB.getAlbumsWithArtistTitle(artistTitle, AlbumDB.KEY_DATE);
		prefs.saveSort(AlbumDB.KEY_DATE);
	}
}
