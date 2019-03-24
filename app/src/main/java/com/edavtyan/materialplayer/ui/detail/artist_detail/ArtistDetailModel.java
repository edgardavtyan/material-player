package com.edavtyan.materialplayer.ui.detail.artist_detail;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.MediaDB;
import com.edavtyan.materialplayer.lib.album_art.AlbumArtProvider;
import com.edavtyan.materialplayer.modular.model.ModelServiceModule;
import com.edavtyan.materialplayer.ui.lists.album_list.AlbumListModel;

import java.util.List;

public class ArtistDetailModel extends AlbumListModel {
	private final MediaDB mediaDB;
	private final ArtistDetailPrefs prefs;
	private final String artistTitle;
	private final ArtistDetailImageLoader imageLoader;

	public ArtistDetailModel(
			ModelServiceModule serviceModule,
			MediaDB mediaDB,
			ArtistDetailImageLoader imageLoader,
			AlbumArtProvider albumArtProvider,
			ArtistDetailPrefs prefs,
			String artistTitle) {
		super(serviceModule, mediaDB, albumArtProvider);
		this.mediaDB = mediaDB;
		this.prefs = prefs;
		this.artistTitle = artistTitle;
		this.imageLoader = imageLoader;
	}

	@Override
	protected List<Album> queryAlbums() {
		return mediaDB.getAlbumsWithArtistTitle(artistTitle, prefs.getSort());
	}

	public Artist getArtist() {
		return mediaDB.getArtistWithTitle(artistTitle);
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
		albums = mediaDB.getAlbumsWithArtistTitle(artistTitle, AlbumDB.KEY_TITLE);
		prefs.saveSort(AlbumDB.KEY_TITLE);
	}

	public void sortByDate() {
		albums = mediaDB.getAlbumsWithArtistTitle(artistTitle, AlbumDB.KEY_DATE);
		prefs.saveSort(AlbumDB.KEY_DATE);
	}
}
