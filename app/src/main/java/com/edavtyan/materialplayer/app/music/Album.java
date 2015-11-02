package com.edavtyan.materialplayer.app.music;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

import lombok.Data;

@Data
public class Album {
    private static final String COLUMN_NAME_ID = MediaStore.Audio.Albums._ID;
    private static final String COLUMN_NAME_TITLE = MediaStore.Audio.Albums.ALBUM;
    private static final String COLUMN_NAME_ARTIST = MediaStore.Audio.Albums.ARTIST;
    private static final String COLUMN_NAME_SONGS_COUNT = MediaStore.Audio.Albums.NUMBER_OF_SONGS;

    private static final int COLUMN_ID = 0;
    private static final int COLUMN_TITLE = 1;
    private static final int COLUMN_ARTIST = 2;
    private static final int COLUMN_SONGS_COUNT = 3;

    private static final Uri URI = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
    private static final String[] PROJECTION = new String[] {
            COLUMN_NAME_ID,
            COLUMN_NAME_TITLE,
            COLUMN_NAME_ARTIST,
            COLUMN_NAME_SONGS_COUNT,
    };

    private int id;
    private String title;
    private String artistTitle;
    private int tracksCount;
    private File art;

    public static Album fromId(int albumId, Context context) {
        Cursor cursor = null;
        Album album = null;
        try {
            cursor = context.getContentResolver().query(
                    URI, PROJECTION,
                    COLUMN_NAME_ID + "=" + albumId,
                    null, null);
            cursor.moveToFirst();

            album = new Album();
            album.setId(albumId);
            album.setTitle(cursor.getString(COLUMN_TITLE));
            album.setArtistTitle(cursor.getString(COLUMN_ARTIST));
            album.setTracksCount(cursor.getInt(COLUMN_SONGS_COUNT));

            Track track = Track.firstTrackOfAlbum(albumId, context);
            album.setArt(ArtProvider.fromTrack(track));
        } finally {
            if (cursor != null) cursor.close();
        }

        return album;
    }
}
