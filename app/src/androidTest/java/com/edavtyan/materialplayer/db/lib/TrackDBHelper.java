package com.edavtyan.materialplayer.db.lib;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;

import com.edavtyan.materialplayer.db.Track;
import com.edavtyan.materialplayer.testlib.db.TestDBHelper;

public class TrackDBHelper extends TestDBHelper {
	private static final String DATABASE_NAME = "testdb_tracks.db";
	private static final int DATABASE_VERSION = 1;

	private static final String TABLE_NAME = "Tracks";

	private static final String KEY_ID = MediaStore.Audio.Media._ID;
	private static final String KEY_TRACK = MediaStore.Audio.Media.TRACK;
	private static final String KEY_TITLE = MediaStore.Audio.Media.TITLE;
	private static final String KEY_DURATION = MediaStore.Audio.Media.DURATION;
	private static final String KEY_PATH = MediaStore.Audio.Media.DATA;
	private static final String KEY_ALBUM_ID = MediaStore.Audio.Media.ALBUM_ID;
	private static final String KEY_ALBUM_TITLE = MediaStore.Audio.Media.ALBUM;
	private static final String KEY_ARTIST_ID = MediaStore.Audio.Media.ARTIST_ID;
	private static final String KEY_ARTIST_TITLE = MediaStore.Audio.Media.ARTIST;
	private static final String KEY_DATE_MODIFIED = MediaStore.Audio.Media.DATE_MODIFIED;

	public TrackDBHelper(Context context) {
		super(context, DATABASE_NAME, DATABASE_VERSION);
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_SQL
				= "CREATE TABLE " + TABLE_NAME + "("
				  + KEY_ID + " INTEGER PRIMARY KEY,"
				  + KEY_TRACK + " INTEGER,"
				  + KEY_TITLE + " TEXT,"
				  + KEY_DURATION + " LONG,"
				  + KEY_PATH + " TEXT,"
				  + KEY_ALBUM_ID + " INTEGER,"
				  + KEY_ALBUM_TITLE + " TEXT,"
				  + KEY_ARTIST_ID + " INTEGER,"
				  + KEY_ARTIST_TITLE + " TEXT,"
				  + KEY_DATE_MODIFIED + " LONG"
				  + ");";

		db.execSQL(CREATE_SQL);
	}

	public void addTrack(Track track) {
		ContentValues values = new ContentValues();
		values.put(KEY_ID, track.getId());
		values.put(KEY_TRACK, track.getTrack());
		values.put(KEY_TITLE, track.getTitle());
		values.put(KEY_DURATION, track.getDuration());
		values.put(KEY_PATH, track.getPath());
		values.put(KEY_ALBUM_ID, track.getAlbumId());
		values.put(KEY_ALBUM_TITLE, track.getAlbumTitle());
		values.put(KEY_ARTIST_ID, track.getArtistId());
		values.put(KEY_ARTIST_TITLE, track.getArtistTitle());
		values.put(KEY_DATE_MODIFIED, track.getDateModified());

		getWritableDatabase().insert(TABLE_NAME, null, values);
	}

	public void addRandomTracks(int count) {
		for (int i = 0; i < count; i++) {
			addTrack(createRandomTrack());
		}
	}

	public void addRandomTracksWhereSomeHaveSameAlbumId(int id, int count) {
		for (int i = 0; i < count; i++) {
			Track track = createRandomTrack();
			track.setAlbumId(id);
			addTrack(track);
		}

		for (int i = 0; i < 5; i++) {
			Track track = createRandomTrack();
			if (track.getAlbumId() == id) track.setAlbumId(id + 1);
			addTrack(track);
		}
	}

	private Track createRandomTrack() {
		Track track = new Track();
		track.setId(RNG.number());
		track.setTitle(RNG.string());
		track.setTrack(RNG.number());
		track.setAlbumId(RNG.number());
		track.setAlbumTitle(RNG.string());
		track.setArtistId(RNG.number());
		track.setArtistTitle(RNG.string());
		track.setDuration(RNG.number());
		track.setDateModified(RNG.number());
		return track;
	}
}
