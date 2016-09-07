package com.edavtyan.materialplayer.db.lib;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;

import com.edavtyan.materialplayer.db.Album;
import com.edavtyan.materialplayer.lib.db.TestDBHelper;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class AlbumDBHelper extends TestDBHelper {
	private static final String DATABASE_NAME = "testMediaStore_Albums.db";
	private static final int DATABASE_VERSION = 1;

	private static final String TABLE_NAME = "Albums";

	private static final String COLUMN_ID = MediaStore.Audio.Albums._ID;
	private static final String COLUMN_TITLE = MediaStore.Audio.Albums.ALBUM;
	private static final String COLUMN_ARTIST_TITLE = MediaStore.Audio.Albums.ARTIST;
	private static final String COLUMN_ART = MediaStore.Audio.Albums.ALBUM_ART;
	private static final String COLUMN_TRACKS_COUNT = MediaStore.Audio.Albums.NUMBER_OF_SONGS;

	private final Faker faker;

	public AlbumDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		faker = new Faker();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE
				= "CREATE TABLE " + TABLE_NAME + "("
				+ COLUMN_ID + " INTEGER PRIMARY KEY,"
				+ COLUMN_TITLE + " TEXT,"
				+ COLUMN_ARTIST_TITLE + " TEXT,"
				+ COLUMN_ART + " TEXT,"
				+ COLUMN_TRACKS_COUNT + " INTEGER"
				+ ");";

		db.execSQL(CREATE_TABLE);
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	public void addAlbum(Album album) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_ID, album.getId());
		values.put(COLUMN_TITLE, album.getTitle());
		values.put(COLUMN_ARTIST_TITLE, album.getArtistTitle());
		values.put(COLUMN_ART, album.getArt());
		values.put(COLUMN_TRACKS_COUNT, album.getTracksCount());

		SQLiteDatabase db = getWritableDatabase();
		db.insert(TABLE_NAME, null, values);
	}

	public void addRandomAlbums(int count) {
		for (int i = 0; i < count; i++) {
			Album album = createRandomAlbum();
			album.setId(i);
			addAlbum(album);
		}
	}

	public List<Album> addRandomAlbumsWhereSomeWithArtistTitle(int count, String title, int withArtistCount) {
		for (int i = 0; i < count - withArtistCount; i++) {
			addAlbum(createRandomAlbum());
		}

		List<Album> albums = new ArrayList<>();

		for (int i = 0; i < withArtistCount; i++) {
			Album album = createRandomAlbum();
			album.setArtistTitle(title);
			addAlbum(album);
			albums.add(album);
		}

		return albums;
	}

	private Album createRandomAlbum() {
		Album album = new Album();
		album.setId((int) faker.number().randomNumber());
		album.setTitle(faker.lorem().characters(10));
		album.setArtistTitle(faker.name().fullName());
		album.setArt(faker.lorem().characters(10));
		album.setTracksCount((int) faker.number().randomNumber());
		return album;
	}
}
