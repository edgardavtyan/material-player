package com.edavtyan.materialplayer.app.models.columns;

import android.net.Uri;
import android.provider.MediaStore;

public class TrackColumns {
	public static final int ID = 0;
	public static final int TRACK = 1;
	public static final int TITLE = 2;
	public static final int DURATION = 3;
	public static final int PATH = 4;
	public static final int ALBUM_ID = 5;
	public static final int ALBUM = 6;
	public static final int ARTIST_ID = 7;
	public static final int ARTIST = 8;
	public static final int DATE_MODIFIED = 9;

	public static final String NAME_ID = MediaStore.Audio.Media._ID;
	public static final String NAME_TRACK = MediaStore.Audio.Media.TRACK;
	public static final String NAME_TITLE = MediaStore.Audio.Media.TITLE;
	public static final String NAME_DURATION = MediaStore.Audio.Media.DURATION;
	public static final String NAME_PATH = MediaStore.Audio.Media.DATA;
	public static final String NAME_ALBUM_ID = MediaStore.Audio.Media.ALBUM_ID;
	public static final String NAME_ALBUM = MediaStore.Audio.Media.ALBUM;
	public static final String NAME_ARTIST_ID = MediaStore.Audio.Media.ARTIST_ID;
	public static final String NAME_ARTIST = MediaStore.Audio.Media.ARTIST;
	public static final String NAME_DATE_MODIFIED = MediaStore.Audio.Media.DATE_MODIFIED;

	public static final Uri URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	public static final String[] PROJECTION = new String[] {
			NAME_ID,
			NAME_TRACK,
			NAME_TITLE,
			NAME_DURATION,
			NAME_PATH,
			NAME_ALBUM_ID,
			NAME_ALBUM,
			NAME_ARTIST_ID,
			NAME_ARTIST,
			NAME_DATE_MODIFIED,
	};
}
