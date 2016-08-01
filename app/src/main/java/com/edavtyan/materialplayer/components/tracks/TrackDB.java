package com.edavtyan.materialplayer.components.tracks;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

import com.edavtyan.materialplayer.lib.models.CursorDB;

import java.util.ArrayList;

public class TrackDB extends CursorDB {
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
	private static final String KEY_PATH = MediaStore.Audio.Media.DATA;
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
			KEY_PATH,
			KEY_ALBUM_ID,
			KEY_ALBUM,
			KEY_ARTIST_ID,
			KEY_ARTIST,
			KEY_DATE_MODIFIED,
	};

	//---

	public TrackDB(Context context) {
		super(context);
	}

	//---

	public CursorLoader getAllTracksLoader() {
		return new CursorLoader(context, URI, PROJECTION, null, null, KEY_TITLE);
	}

	public CursorLoader getAlbumTracksLoader(int albumId) {
		String selection = KEY_ALBUM_ID + "=" + albumId;
		return new CursorLoader(context, URI, PROJECTION, selection, null, KEY_TRACK);
	}

	public Track getTrack(int position) {
		cursor.moveToPosition(position);
		return getTrackFromCursor(cursor);
	}

	public ArrayList<Track> getAllTracks() {
		return getAllTracksFromCursor(cursor);
	}

	public ArrayList<Track> getAllTracksWithAlbumId(int albumId) {
		Cursor cursor = null;
		String selection = KEY_ALBUM_ID + "=" + albumId;
		try {
			cursor = resolver.query(URI, PROJECTION, selection, null, KEY_TITLE);
			return getAllTracksFromCursor(cursor);
		} finally {
			if (cursor != null) cursor.close();
		}
	}

	public Track getSingleTrackWithAlbumId(int albumId) {
		Track track = new Track();
		Cursor cursor = null;
		String selection = KEY_ALBUM_ID + "=" + albumId;
		try {
			cursor = resolver.query(URI, PROJECTION, selection, null, null);
			cursor.moveToFirst();
			track = getTrackFromCursor(cursor);
		} finally {
			if (cursor != null) cursor.close();
		}

		return track;
	}

	public Track getSingleTrackWithId(int id) {
		Track track = new Track();
		Cursor cursor = null;
		String selection = KEY_ID + "=" + id;
		try {
			cursor = resolver.query(URI, PROJECTION, selection, null, null);
			cursor.moveToFirst();
			track = getTrackFromCursor(cursor);
		} finally {
			if (cursor != null) cursor.close();
		}

		return track;
	}

	//---

	private Track getTrackFromCursor(Cursor cursor) {
		Track track = new Track();
		track.setId(cursor.getInt(COLUMN_ID));
		track.setTrack(cursor.getInt(COLUMN_TRACK));
		track.setTitle(cursor.getString(COLUMN_TITLE));
		track.setDuration(cursor.getLong(COLUMN_DURATION));
		track.setAlbumId(cursor.getInt(COLUMN_ALBUM_ID));
		track.setAlbumTitle(cursor.getString(COLUMN_ALBUM));
		track.setArtistId(cursor.getInt(COLUMN_ARTIST_ID));
		track.setArtistTitle(cursor.getString(COLUMN_ARTIST));
		track.setPath(cursor.getString(COLUMN_PATH));
		track.setDateModified(cursor.getLong(COLUMN_DATE_MODIFIED) * 1000);
		return track;
	}

	private ArrayList<Track> getAllTracksFromCursor(Cursor cursor) {
		ArrayList<Track> tracks = new ArrayList<>();
		cursor.moveToPosition(-1);
		int queueIndex = 0;
		while (cursor.moveToNext()) {
			Track track = getTrackFromCursor(cursor);
			track.setQueueIndex(queueIndex);
			tracks.add(track);
			queueIndex++;
		}

		return tracks;
	}
}
