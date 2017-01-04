package com.edavtyan.materialplayer.components.artist_detail;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.edavtyan.materialplayer.components.album_all.AlbumListModel;
import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.db.AlbumDB;
import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.db.ArtistDB;
import com.edavtyan.materialplayer.db.TrackDB;
import com.edavtyan.materialplayer.lib.mvp.list.CompactListPref;
import com.edavtyan.materialplayer.utils.ArtistArtProvider;

import java.util.List;

import lombok.Setter;

public class ArtistDetailModel extends AlbumListModel implements ArtistDetailMvp.Model {
	private final ArtistDB artistDB;
	private final AlbumDB albumDB;
	private final String artistTitle;
	private final ArtistArtProvider artistArtProvider;

	private @Setter OnArtLoadedListener onArtLoadedListener;

	private class ArtistImageTask extends AsyncTask<String, Void, Bitmap> {
		@Override
		protected Bitmap doInBackground(String... title) {
			return artistArtProvider.load(title[0]);
		}

		@Override
		protected void onPostExecute(Bitmap art) {
			if (onArtLoadedListener != null) onArtLoadedListener.onArtistImageLoaded(art);
		}
	}

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

	public void loadArtistImage() {
		new ArtistImageTask().execute(artistTitle);
	}
}
