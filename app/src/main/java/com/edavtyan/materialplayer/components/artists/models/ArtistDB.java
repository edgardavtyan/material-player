package com.edavtyan.materialplayer.components.artists.models;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

import com.edavtyan.materialplayer.lib.models.CursorDB;

public class ArtistDB extends CursorDB {
	private static final int COLUMN_ID = 0;
	private static final int COLUMN_TITLE = 1;
	private static final int COLUMN_ALBUMS_COUNT = 2;
	private static final int COLUMN_TRACKS_COUNT = 3;

	private static final String KEY_ID = MediaStore.Audio.Artists._ID;
	private static final String KEY_TITLE = MediaStore.Audio.Artists.ARTIST;
	private static final String KEY_ALBUMS_COUNT = MediaStore.Audio.Artists.NUMBER_OF_ALBUMS;
	private static final String KEY_TRACKS_COUNT = MediaStore.Audio.Artists.NUMBER_OF_TRACKS;

	private static final Uri URI = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
	private static final String[] PROJECTION = {
			KEY_ID,
			KEY_TITLE,
			KEY_ALBUMS_COUNT,
			KEY_TRACKS_COUNT,
	};

	//---

	public ArtistDB(Context context) {
		super(context);
	}

	//---

	public CursorLoader getAllArtistsLoader() {
		return new CursorLoader(context, URI, PROJECTION, null, null, null);
	}

	public Artist getArtist(int position) {
		cursor.moveToPosition(position);
		return getArtistFromCursor(cursor);
	}

	public Artist getArtistFromTitle(String title) {
		Cursor cursor = null;
		String selection = KEY_TITLE + "=?";
		String[] args = {title};

		try {
			cursor = resolver.query(URI, PROJECTION, selection, args, KEY_TITLE);
			cursor.moveToFirst();
			return getArtistFromCursor(cursor);
		} finally {
			if (cursor != null) cursor.close();
		}
	}

	//---

	private Artist getArtistFromCursor(Cursor cursor) {
		Artist artist = new Artist();
		artist.setId(cursor.getInt(COLUMN_ID));
		artist.setTitle(cursor.getString(COLUMN_TITLE));
		artist.setAlbumsCount(cursor.getInt(COLUMN_ALBUMS_COUNT));
		artist.setTracksCount(cursor.getInt(COLUMN_TRACKS_COUNT));
		return artist;
	}
}
