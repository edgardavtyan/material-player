package com.edavtyan.materialplayer.app.models.track;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

import java.util.ArrayList;

public class TracksProvider {
	public static final int COLUMN_ID = 0;
	public static final int COLUMN_TRACK = 1;
	public static final int COLUMN_TITLE = 2;
	public static final int COLUMN_DURATION = 3;
	public static final int COLUMN_PATH = 4;
	public static final int COLUMN_ALBUM_ID = 5;
	public static final int COLUMN_ALBUM = 6;
	public static final int COLUMN_ARTIST_ID = 7;
	public static final int COLUMN_ARTIST = 8;
	public static final int COLUMN_DATE_MODIFIED = 9;

	private static final String KEY_ID = MediaStore.Audio.Media._ID;
	private static final String KEY_TRACK = MediaStore.Audio.Media.TRACK;
	private static final String KEY_TITLE = MediaStore.Audio.Media.TITLE;
	private static final String KEY_DURATION = MediaStore.Audio.Media.DURATION;
	private static final String KEY_DATA = MediaStore.Audio.Media.DATA;
	private static final String KEY_ALBUM_ID = MediaStore.Audio.Media.ALBUM_ID;
	private static final String KEY_ALBUM = MediaStore.Audio.Media.ALBUM;
	private static final String KEY_ARTIST_ID = MediaStore.Audio.Media.ARTIST_ID;
	private static final String KEY_ARTIST = MediaStore.Audio.Media.ARTIST;
	private static final String KEY_DATE_MODIFIED = MediaStore.Audio.Media.DATE_MODIFIED;

	private static final Uri URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	private static final String[] PROJECTION = {
			KEY_ID,
			KEY_TRACK,
			KEY_TITLE,
			KEY_DURATION,
			KEY_DATA,
			KEY_ALBUM_ID,
			KEY_ALBUM,
			KEY_ARTIST_ID,
			KEY_ARTIST,
			KEY_DATE_MODIFIED,

	};

	/* Fields */

	private final Context context;
	private final ContentResolver resolver;

	/* Constructors */

	public TracksProvider(Context context) {
		this.context = context;
		resolver = context.getContentResolver();
	}

	/* Public methods */

	public int getId(Cursor cursor) {
		return cursor.getInt(COLUMN_ID);
	}

	public int getIndex(Cursor cursor) {
		return cursor.getInt(COLUMN_TRACK);
	}

	public String getTitle(Cursor cursor) {
		return cursor.getString(COLUMN_TITLE);
	}

	public long getDuration(Cursor cursor) {
		return cursor.getLong(COLUMN_DURATION);
	}

	public String getPath(Cursor cursor) {
		return cursor.getString(COLUMN_PATH);
	}

	public int getAlbumId(Cursor cursor) {
		return cursor.getInt(COLUMN_ALBUM_ID);
	}

	public String getAlbum(Cursor cursor) {
		return cursor.getString(COLUMN_ALBUM);
	}

	public int getArtistId(Cursor cursor) {
		return cursor.getInt(COLUMN_ARTIST_ID);
	}

	public String getArtist(Cursor cursor) {
		return cursor.getString(COLUMN_ARTIST);
	}

	public long getDateModified(Cursor cursor) {
		return cursor.getLong(COLUMN_DATE_MODIFIED);
	}

	// ---

	public CursorLoader getAllTracksLoader() {
		return new CursorLoader(context, URI, PROJECTION, null, null, KEY_TITLE);
	}

	public CursorLoader getAlbumTracksLoader(int albumId) {
		String selection = KEY_ALBUM_ID + "=" + albumId;
		return new CursorLoader(context, URI, PROJECTION, selection, null, KEY_TRACK);
	}

	// ---

	public ArrayList<Track> getAllTracks(Cursor cursor) {
		ArrayList<Track> tracks = new ArrayList<>();
		cursor.moveToPosition(-1);
		int queueIndex = 0;
		while (cursor.moveToNext()) {
			Track track = getTrack(cursor);
			track.setQueueIndex(queueIndex);
			tracks.add(track);
			queueIndex++;
		}

		return tracks;
	}

	public Track getTrack(Cursor cursor) {
		Track track = new Track();
		track.setTrackId(getId(cursor));
		track.setTrackTitle(getTitle(cursor));
		track.setDuration(getDuration(cursor));
		track.setAlbumId(getAlbumId(cursor));
		track.setArtistTitle(getArtist(cursor));
		track.setAlbumTitle(getAlbum(cursor));
		track.setPath(getPath(cursor));
		track.setDateModified(getDateModified(cursor) * 1000);
		return track;
	}

	public ArrayList<Track> getAllTracksWithAlbumId(int albumId) {
		Cursor cursor = null;
		try {
			cursor = resolver.query(URI, PROJECTION, KEY_ALBUM_ID + "=" + albumId, null, KEY_TITLE);
			return getAllTracks(cursor);
		} finally {
			if (cursor != null) cursor.close();
		}
	}

	public Track getSingleTrackWithAlbumId(int albumId) {
		Track track = new Track();
		Cursor cursor = null;
		try {
			cursor = resolver.query(URI, PROJECTION, KEY_ALBUM_ID + "=" + albumId, null, null);
			cursor.moveToFirst();
			track = getTrack(cursor);
		} finally {
			if (cursor != null) cursor.close();
		}

		return track;
	}

	public Track getSingleTrackWithId(int id) {
		Track track = new Track();
		Cursor cursor = null;
		try {
			track.setTrackId(id);
			cursor = resolver.query(URI, PROJECTION, KEY_ID + "=" + id, null, null);
			cursor.moveToFirst();
			track = getTrack(cursor);
		} finally {
			if (cursor != null) cursor.close();
		}

		return track;
	}
}
