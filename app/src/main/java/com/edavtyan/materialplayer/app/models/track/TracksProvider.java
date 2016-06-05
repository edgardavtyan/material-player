package com.edavtyan.materialplayer.app.models.track;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

import java.util.ArrayList;

public class TracksProvider {
	/*
	 * Fields
	 */

	private final Context context;

	/*
	 * Constants
	 */

	private static final Uri URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	private static final String[] PROJECTION = {
			MediaStore.Audio.Media._ID,
			MediaStore.Audio.Media.TRACK,
			MediaStore.Audio.Media.TITLE,
			MediaStore.Audio.Media.DURATION,
			MediaStore.Audio.Media.DATA,
			MediaStore.Audio.Media.ALBUM_ID,
			MediaStore.Audio.Media.ALBUM,
			MediaStore.Audio.Media.ARTIST_ID,
			MediaStore.Audio.Media.ARTIST,
			MediaStore.Audio.Media.DATE_MODIFIED,

	};

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

	/*
	 * Constructors
	 */

	public TracksProvider(Context context) {
		this.context = context;
	}

	/*
	 * Public methods
	 */

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
		return new CursorLoader(
				context,
				URI,
				PROJECTION,
				null, null,
				MediaStore.Audio.Media.TITLE);
	}

	public CursorLoader getAlbumTracksLoader(int albumId) {
		return new CursorLoader(
				context, URI, PROJECTION,
				MediaStore.Audio.Media.ALBUM_ID + "=" + albumId,
				null,
				MediaStore.Audio.Media.TRACK);
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
			cursor = context.getContentResolver().query(
					URI, PROJECTION,
					MediaStore.Audio.Media.ALBUM_ID + "=" + albumId,
					null,
					MediaStore.Audio.Media.TITLE);
			return getAllTracks(cursor);
		} finally {
			if (cursor != null) cursor.close();
		}
	}

	public Track getSingleTrackWithAlbumId(int albumId) {
		Track track = new Track();
		Cursor cursor = null;
		try {
			cursor = context.getContentResolver().query(
					URI, PROJECTION,
					MediaStore.Audio.Media.ALBUM_ID + "=" + albumId,
					null, null);
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

			cursor = context.getContentResolver().query(
					URI, PROJECTION,
					MediaStore.Audio.Media._ID + "=" + id,
					null, null);
			cursor.moveToFirst();
			track = getTrack(cursor);
		} finally {
			if (cursor != null) cursor.close();
		}

		return track;
	}
}
