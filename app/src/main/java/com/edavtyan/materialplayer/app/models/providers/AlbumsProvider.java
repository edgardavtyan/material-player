package com.edavtyan.materialplayer.app.models.providers;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

public class AlbumsProvider {
	/*
	 * Fields
	 */

	private final Context context;

	/*
	 * Constants
	 */

	private static final Uri URI = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
	private static final String[] PROJECTION = {
			MediaStore.Audio.Albums._ID,
			MediaStore.Audio.Albums.ALBUM,
			MediaStore.Audio.Albums.ARTIST,
			MediaStore.Audio.Albums.NUMBER_OF_SONGS,
			MediaStore.Audio.Albums.ALBUM_ART,
	};

	private static final int COLUMN_ID = 0;
	private static final int COLUMN_TITLE = 1;
	private static final int COLUMN_ARTIST = 2;
	private static final int COLUMN_TRACKS_COUNT = 3;
	private static final int COLUMN_ART_PATH = 4;

	/*
	 * Constructors
	 */

	public AlbumsProvider(Context context) {
		this.context = context;
	}

	/*
	 * Public methods
	 */

	public int getId(Cursor cursor) {
		return cursor.getInt(COLUMN_ID);
	}

	public String getTitle(Cursor cursor) {
		return cursor.getString(COLUMN_TITLE);
	}

	public String getArtist(Cursor cursor) {
		return cursor.getString(COLUMN_ARTIST);
	}

	public int getTracksCount(Cursor cursor) {
		return cursor.getInt(COLUMN_TRACKS_COUNT);
	}

	public String getArtPath(Cursor cursor) {
		return cursor.getString(COLUMN_ART_PATH);
	}

	public CursorLoader getAllAlbumsLoader() {
		return new CursorLoader(
				context,
				URI,
				PROJECTION,
				null, null,
				MediaStore.Audio.Albums.ALBUM);
	}
}
