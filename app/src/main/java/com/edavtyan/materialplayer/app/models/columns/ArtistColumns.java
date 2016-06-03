package com.edavtyan.materialplayer.app.models.columns;

import android.net.Uri;
import android.provider.MediaStore;

public class ArtistColumns {
	public static final String NAME_ID = MediaStore.Audio.Artists._ID;
	public static final String NAME_TITLE = MediaStore.Audio.Artists.ARTIST;
	public static final String NAME_ALBUMS_COUNT = MediaStore.Audio.Artists.NUMBER_OF_ALBUMS;
	public static final String NAME_TRACKS_COUNT = MediaStore.Audio.Artists.NUMBER_OF_TRACKS;

	public static final int ID = 0;
	public static final int TITLE = 1;
	public static final int ALBUMS_COUNT = 2;
	public static final int TRACKS_COUNT = 3;

	public static final Uri URI = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
	public static final String[] PROJECTION = {
			NAME_ID,
			NAME_TITLE,
			NAME_ALBUMS_COUNT,
			NAME_TRACKS_COUNT,
	};
}
