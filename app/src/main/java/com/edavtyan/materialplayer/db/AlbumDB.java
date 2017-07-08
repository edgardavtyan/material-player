package com.edavtyan.materialplayer.db;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.edavtyan.materialplayer.utils.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Cleanup;

public class AlbumDB {
	private static final String KEY_ID = MediaStore.Audio.Albums._ID;
	private static final String KEY_TITLE = MediaStore.Audio.Albums.ALBUM;
	private static final String KEY_ARTIST_TITLE = MediaStore.Audio.Albums.ARTIST;
	private static final String KEY_ART = MediaStore.Audio.Albums.ALBUM_ART;
	private static final String KEY_TRACKS_COUNT = MediaStore.Audio.Albums.NUMBER_OF_SONGS;

	private static final int INDEX_ID = 0;
	private static final int INDEX_TITLE = 1;
	private static final int INDEX_ARTIST_TITLE = 2;
	private static final int INDEX_ART = 3;
	private static final int INDEX_TRACKS_COUNT = 4;

	private static final Uri URI = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
	private static final String[] PROJECTION = {
			KEY_ID,
			KEY_TITLE,
			KEY_ARTIST_TITLE,
			KEY_ART,
			KEY_TRACKS_COUNT,
	};

	private final ContentResolver resolver;

	public AlbumDB(Context context) {
		resolver = context.getContentResolver();
	}

	public List<Album> getAllAlbums() {
		return getListOfAlbums(null, null, KEY_TITLE);
	}

	public List<Album> getAlbumsWithArtistTitle(String artistTitle) {
		String selection = KEY_ARTIST_TITLE + "=?";
		String[] args = {artistTitle};
		return getListOfAlbums(selection, args, KEY_TITLE);
	}

	public Album getAlbumWithAlbumId(int albumId) {
		String selection = KEY_ID + "=?";
		String[] args = {Integer.toString(albumId)};
		return getListOfAlbums(selection, args, null).get(0);
	}

	public List<Album> searchAlbums(String albumTitle) {
		if (Strings.nullOrEmpty(albumTitle)) return Collections.emptyList();
		String selection = KEY_TITLE + " LIKE ?";
		String[] args = new String[]{"%" + albumTitle + "%"};
		return getListOfAlbums(selection, args, KEY_TITLE);
	}

	private Album getAlbumFromCursor(Cursor cursor) {
		Album album = new Album();
		album.setId(cursor.getInt(INDEX_ID));
		album.setTitle(cursor.getString(INDEX_TITLE));
		album.setArtistTitle(cursor.getString(INDEX_ARTIST_TITLE));
		album.setArt(cursor.getString(INDEX_ART));
		album.setTracksCount(cursor.getInt(INDEX_TRACKS_COUNT));
		return album;
	}

	private List<Album> getListOfAlbums(String selection, String[] args, String order) {
		@Cleanup
		Cursor cursor = resolver.query(URI, PROJECTION, selection, args, order);
		List<Album> albums = new ArrayList<>();

		while (cursor.moveToNext()) {
			albums.add(getAlbumFromCursor(cursor));
		}

		return albums;
	}
}
