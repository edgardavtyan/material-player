package com.edavtyan.materialplayer.db;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import lombok.Cleanup;

public class ArtistDB {
	public static final String KEY_ID = MediaStore.Audio.Artists._ID;
	public static final String KEY_TITLE = MediaStore.Audio.Artists.ARTIST;
	public static final String KEY_ALBUMS_COUNT = MediaStore.Audio.Artists.NUMBER_OF_ALBUMS;
	public static final String KEY_TRACKS_COUNT = MediaStore.Audio.Artists.NUMBER_OF_TRACKS;

	public static final int INDEX_ID = 0;
	public static final int INDEX_TITLE = 1;
	public static final int INDEX_ALBUMS_COUNT = 2;
	public static final int INDEX_TRACKS_COUNT = 3;

	public static final Uri URI = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
	public static final String[] PROJECTION = {
			KEY_ID, KEY_TITLE, KEY_ALBUMS_COUNT, KEY_TRACKS_COUNT
	};

	private final ContentResolver resolver;

	public ArtistDB(Context context) {
		resolver = context.getContentResolver();
	}

	public List<Artist> getAllArtists() {
		return getArtistsFromCursor(null, null, KEY_TITLE);
	}

	public Artist getArtistWithTitle(String artistTitle) {
		String selection = KEY_TITLE + "=?";
		String[] args = {artistTitle};
		return getArtistsFromCursor(selection, args, null).get(0);
	}

	public List<Artist> searchArtists(String artistTitle) {
		String selection = KEY_TITLE + " LIKE ?";
		String[] args = new String[]{"%" + artistTitle + "%"};
		return getArtistsFromCursor(selection, args, KEY_TITLE);
	}

	private Artist getArtistFromCursor(Cursor cursor) {
		Artist artist = new Artist();
		artist.setId(cursor.getInt(INDEX_ID));
		artist.setTitle(cursor.getString(INDEX_TITLE));
		artist.setAlbumsCount(cursor.getInt(INDEX_ALBUMS_COUNT));
		artist.setTracksCount(cursor.getInt(INDEX_TRACKS_COUNT));
		return artist;
	}

	private List<Artist> getArtistsFromCursor(String selection, String[] args, String sort) {
		@Cleanup
		Cursor cursor = resolver.query(URI, PROJECTION, selection, args, sort);
		List<Artist> artists = new ArrayList<>();

		while (cursor.moveToNext()) {
			artists.add(getArtistFromCursor(cursor));
		}

		return artists;
	}
}
