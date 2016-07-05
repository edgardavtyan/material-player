package com.edavtyan.materialplayer.components.albums.models;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

import com.edavtyan.materialplayer.components.tracks.Track;
import com.edavtyan.materialplayer.components.tracks.TracksProvider;
import com.edavtyan.materialplayer.lib.models.CursorDB;
import com.edavtyan.materialplayer.utils.ArtProvider;

public class AlbumDB extends CursorDB {
	private static final int COLUMN_ID = 0;
	private static final int COLUMN_TITLE = 1;
	private static final int COLUMN_ARTIST = 2;
	private static final int COLUMN_TRACKS_COUNT = 3;
	private static final int COLUMN_ART_PATH = 4;

	private static final String KEY_ID = MediaStore.Audio.Albums._ID;
	private static final String KEY_TITLE = MediaStore.Audio.Albums.ALBUM;
	private static final String KEY_ARTIST = MediaStore.Audio.Albums.ARTIST;
	private static final String KEY_TRACKS_COUNT = MediaStore.Audio.Albums.NUMBER_OF_SONGS;
	private static final String KEY_ART_PATH = MediaStore.Audio.Albums.ALBUM_ART;

	private static final Uri URI = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
	private static final String[] PROJECTION = {
			KEY_ID,
			KEY_TITLE,
			KEY_ARTIST,
			KEY_TRACKS_COUNT,
			KEY_ART_PATH,
	};

	/*
	 * Constructors
	 */

	public AlbumDB(Context context) {
		super(context);
	}

	/*
	 * Public methods
	 */

	public Album getAlbum(int position) {
		cursor.moveToPosition(position);

		Album album = new Album();
		album.setId(cursor.getInt(COLUMN_ID));
		album.setTitle(cursor.getString(COLUMN_TITLE));
		album.setArtistTitle(cursor.getString(COLUMN_ARTIST));
		album.setTracksCount(cursor.getInt(COLUMN_TRACKS_COUNT));
		album.setArt(ArtProvider.fromPath(cursor.getString(COLUMN_ART_PATH)));

		return album;
	}

	//---

	public CursorLoader getAllAlbumsLoader() {
		return new CursorLoader(context, URI, PROJECTION, null, null, KEY_TITLE);
	}

	public CursorLoader getArtistAlbumsLoader(String artist) {
		String selection = String.format("%s=?", KEY_ARTIST);
		String[] args = new String[] {artist};
		return new CursorLoader(context, URI, PROJECTION, selection, args, KEY_TITLE);
	}

	//---

	public Album getAlbumFromId(int id) {
		Cursor cursor = null;
		Album album = null;
		TracksProvider tracksProvider = new TracksProvider(context);
		try {
			cursor = resolver.query(URI, PROJECTION, KEY_ID + "=" + id, null, null);
			cursor.moveToFirst();

			album = new Album();
			album.setId(id);
			album.setTitle(cursor.getString(COLUMN_TITLE));
			album.setArtistTitle(cursor.getString(COLUMN_ARTIST));
			album.setTracksCount(cursor.getInt(COLUMN_TRACKS_COUNT));

			Track track = tracksProvider.getSingleTrackWithAlbumId(id);
			album.setArt(ArtProvider.fromTrack(track));
		} finally {
			if (cursor != null) cursor.close();
		}

		return album;
	}
}
