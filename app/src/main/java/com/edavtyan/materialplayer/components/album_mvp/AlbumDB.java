package com.edavtyan.materialplayer.components.album_mvp;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.edavtyan.materialplayer.components.albums.Album;
import com.edavtyan.materialplayer.utils.ArtProvider;

import java.util.ArrayList;
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
		@Cleanup Cursor cursor = resolver.query(URI, PROJECTION, null, null, KEY_TITLE);
		List<Album> albums = new ArrayList<>();
		while (cursor.moveToNext()) albums.add(getAlbumFromCursor(cursor));
		return albums;
	}

	private Album getAlbumFromCursor(Cursor cursor) {
		Album album = new Album();
		album.setId(cursor.getInt(INDEX_ID));
		album.setTitle(cursor.getString(INDEX_TITLE));
		album.setArtistTitle(cursor.getString(INDEX_ARTIST_TITLE));
		album.setArt(ArtProvider.fromPath(cursor.getString(INDEX_ART)));
		album.setTracksCount(cursor.getInt(INDEX_TRACKS_COUNT));
		return album;
	}
}
