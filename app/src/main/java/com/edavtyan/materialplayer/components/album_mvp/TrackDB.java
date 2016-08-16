package com.edavtyan.materialplayer.components.album_mvp;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.edavtyan.materialplayer.components.tracks.Track;

import java.util.ArrayList;
import java.util.List;

import lombok.Cleanup;

public class TrackDB {
	private static final int INDEX_ID = 0;
	private static final int INDEX_TRACK = 1;
	private static final int INDEX_TITLE = 2;
	private static final int INDEX_DURATION = 3;
	private static final int INDEX_PATH = 4;
	private static final int INDEX_ALBUM_ID = 5;
	private static final int INDEX_ALBUM = 6;
	private static final int INDEX_ARTIST_ID = 7;
	private static final int INDEX_ARTIST = 8;
	private static final int INDEX_DATE_MODIFIED = 9;

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

	private final ContentResolver resolver;

	public TrackDB(Context context) {
		resolver = context.getContentResolver();
	}

	public List<Track> getTracksWithAlbumId(int albumId) {
		String selection = KEY_ALBUM_ID + "=?";
		String[] args = { Integer.toString(albumId) };
		@Cleanup Cursor cursor = resolver.query(URI, PROJECTION, selection, args, KEY_TRACK);

		List<Track> tracks = new ArrayList<>();

		while (cursor.moveToNext()) {
			tracks.add(getTrackFromCursor(cursor));
		}

		return tracks;
	}

	public List<Track> getAllTracks() {
		@Cleanup Cursor cursor = resolver.query(URI, PROJECTION, null, null, KEY_TITLE);
		List<Track> tracks = new ArrayList<>();

		while (cursor.moveToNext()) {
			tracks.add(getTrackFromCursor(cursor));
		}

		return tracks;
	}

	private Track getTrackFromCursor(Cursor cursor) {
		Track track = new Track();
		track.setId(cursor.getInt(INDEX_ID));
		track.setTrack(cursor.getInt(INDEX_TRACK));
		track.setTitle(cursor.getString(INDEX_TITLE));
		track.setDuration(cursor.getLong(INDEX_DURATION));
		track.setAlbumId(cursor.getInt(INDEX_ALBUM_ID));
		track.setAlbumTitle(cursor.getString(INDEX_ALBUM));
		track.setArtistId(cursor.getInt(INDEX_ARTIST_ID));
		track.setArtistTitle(cursor.getString(INDEX_ARTIST));
		track.setPath(cursor.getString(INDEX_PATH));
		track.setDateModified(cursor.getLong(INDEX_DATE_MODIFIED) * 1000);
		return track;
	}
}
