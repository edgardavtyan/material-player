package com.edavtyan.materialplayer.app.music.data;

import android.content.Context;
import android.database.Cursor;

import com.edavtyan.materialplayer.app.music.columns.ArtistColumns;

import lombok.Data;

@Data
public class Artist {
    private String title;
    private int tracksCount;
    private int albumsCount;

    public static Artist fromTitle(Context context, String title) {
        Cursor cursor = null;

        try {
            cursor = context.getContentResolver().query(
                    ArtistColumns.URI,
                    ArtistColumns.PROJECTION,
                    ArtistColumns.NAME_TITLE + "='" + title + "'",
                    null, null);
            cursor.moveToFirst();

            Artist artist = new Artist();
            artist.setTitle(cursor.getString(ArtistColumns.TITLE));
            artist.setAlbumsCount(cursor.getInt(ArtistColumns.ALBUMS_COUNT));
            artist.setTracksCount(cursor.getInt(ArtistColumns.TRACKS_COUNT));
            return artist;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
