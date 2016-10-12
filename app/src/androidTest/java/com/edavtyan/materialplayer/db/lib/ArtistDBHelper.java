package com.edavtyan.materialplayer.db.lib;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;

import com.edavtyan.materialplayer.db.Artist;
import com.edavtyan.materialplayer.lib.db.TestDBHelper;
import com.github.javafaker.Faker;

public class ArtistDBHelper extends TestDBHelper {
	private static final String TABLE_NAME = "Artists";
	private static final String DATABASE_NAME = "testdb_artists.db";
	private static final int DATABASE_VERSION = 1;
	private static final String ID = MediaStore.Audio.Artists._ID;
	private static final String TITLE = MediaStore.Audio.Artists.ARTIST;
	private static final String ALBUMS_COUNT = MediaStore.Audio.Artists.NUMBER_OF_ALBUMS;
	private static final String TRACKS_COUNT = MediaStore.Audio.Artists.NUMBER_OF_TRACKS;

	private Faker faker;

	public ArtistDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		faker = new Faker();
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_SQL
				= "CREATE TABLE " + TABLE_NAME + "("
				+ ID + " INTEGER PRIMARY KEY,"
				+ TITLE + " TEXT,"
				+ ALBUMS_COUNT + " INTEGER,"
				+ TRACKS_COUNT + " INTEGER"
				+ ");";

		db.execSQL(CREATE_SQL);
	}

	private void addArtist(Artist artist) {
		ContentValues values = new ContentValues();
		values.put(ID, artist.getId());
		values.put(TITLE, artist.getTitle());
		values.put(ALBUMS_COUNT, artist.getAlbumsCount());
		values.put(TRACKS_COUNT, artist.getTracksCount());
		getWritableDatabase().insert(TABLE_NAME, null, values);
	}

	public Artist addRandomArtist() {
		Artist artist = createRandomArtist();
		addArtist(artist);
		return artist;
	}

	public void addRandomArtists(int count) {
		for (int i = 0; i < count; i++) {
			Artist artist = createRandomArtist();
			artist.setId(i);
			addArtist(artist);
		}
	}

	private Artist createRandomArtist() {
		Artist artist = new Artist();
		artist.setId((int) faker.number().randomNumber());
		artist.setTitle(faker.name().fullName());
		artist.setAlbumsCount((int) faker.number().randomNumber());
		artist.setTracksCount((int) faker.number().randomNumber());
		return artist;
	}
}
