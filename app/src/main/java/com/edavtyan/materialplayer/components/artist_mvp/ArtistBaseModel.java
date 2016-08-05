package com.edavtyan.materialplayer.components.artist_mvp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.edavtyan.materialplayer.components.artists.Artist;

public class ArtistBaseModel {
	public static final String ID = MediaStore.Audio.Artists._ID;
	public static final String TITLE = MediaStore.Audio.Artists.ARTIST;
	public static final String ALBUMS_COUNT = MediaStore.Audio.Artists.NUMBER_OF_ALBUMS;
	public static final String TRACKS_COUNT = MediaStore.Audio.Artists.NUMBER_OF_TRACKS;
	public static final int INDEX_ID = 0;
	public static final int INDEX_TITLE = 1;
	public static final int INDEX_ALBUMS_COUNT = 2;
	public static final int INDEX_TRACKS_COUNT = 3;
	public static final Uri URI_EXTERNAL = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
	public static final String[] PROJECTION = {ID, TITLE, ALBUMS_COUNT, TRACKS_COUNT};

	protected final ContentResolver resolver;

	public ArtistBaseModel(ContentResolver resolver) {
		this.resolver = resolver;
	}

	protected Artist getArtistFromCursor(Cursor cursor) {
		Artist artist = new Artist();
		artist.setId(cursor.getInt(INDEX_ID));
		artist.setTitle(cursor.getString(INDEX_TITLE));
		artist.setAlbumsCount(cursor.getInt(INDEX_ALBUMS_COUNT));
		artist.setTracksCount(cursor.getInt(INDEX_TRACKS_COUNT));
		return artist;
	}
}
