package com.edavtyan.materialplayer.app.music.columns;

import android.net.Uri;
import android.provider.MediaStore;

public class AlbumColumns {
	public static final String NAME_ID = MediaStore.Audio.Albums._ID;
	public static final String NAME_TITLE = MediaStore.Audio.Albums.ALBUM;
	public static final String NAME_ARTIST = MediaStore.Audio.Albums.ARTIST;
	public static final String NAME_SONGS_COUNT = MediaStore.Audio.Albums.NUMBER_OF_SONGS;
	public static final String NAME_ART = MediaStore.Audio.Albums.ALBUM_ART;

	public static final int ID = 0;
	public static final int TITLE = 1;
	public static final int ARTIST = 2;
	public static final int SONGS_COUNT = 3;
	public static final int ART = 4;

	public static final Uri URI = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
	public static final String[] PROJECTION = {
			NAME_ID,
			NAME_TITLE,
			NAME_ARTIST,
			NAME_SONGS_COUNT,
			NAME_ART,
	};
}
