package com.edavtyan.materialplayer.components.artists;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

public class ArtistsProvider {
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

	/* Fields */

	private final Context context;
	private final ContentResolver resolver;

	/* Constructors */

	public ArtistsProvider(Context context) {
		this.context = context;
		resolver = context.getContentResolver();
	}

	/* Public methods */

	public int getId(Cursor cursor) {
		return cursor.getInt(COLUMN_ID);
	}

	public String getTitle(Cursor cursor) {
		return cursor.getString(COLUMN_TITLE);
	}

	public int getAlbumsCount(Cursor cursor) {
		return cursor.getInt(COLUMN_ALBUMS_COUNT);
	}

	public int getTracksCount(Cursor cursor) {
		return cursor.getInt(COLUMN_TRACKS_COUNT);
	}

	//---

	public CursorLoader getAllArtistsLoader() {
		return new CursorLoader(context, URI, PROJECTION, null, null, null);
	}

	//---

	public Artist getArtistFromTitle(String title) {
		Cursor cursor = null;

		String selection = String.format("%s=?", KEY_TITLE);
		String[] args = {title};

		try {
			cursor = resolver.query(URI, PROJECTION, selection, args, KEY_TITLE);
			cursor.moveToFirst();

			Artist artist = new Artist();
			artist.setTitle(getTitle(cursor));
			artist.setAlbumsCount(getAlbumsCount(cursor));
			artist.setTracksCount(getTracksCount(cursor));
			return artist;
		} finally {
			if (cursor != null) cursor.close();
		}
	}
}
