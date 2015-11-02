package com.edavtyan.materialplayer.app.music;

import android.content.Context;
import android.database.Cursor;

import com.edavtyan.materialplayer.app.music.columns.TrackColumns;

import java.io.File;

import lombok.Data;

@Data
public class Metadata {
    private int trackId;
    private int albumId;
    private long duration;
    private String trackTitle;
    private String artistTitle;
    private String albumTitle;
    private String path;
    private long dateModified;



    public static Metadata fromId(int id, Context context) {
        Metadata metadata = new Metadata();
        Cursor cursor = null;
        try {
            metadata.setTrackId(id);

            cursor = context.getContentResolver().query(
                    TrackColumns.URI,
                    TrackColumns.PROJECTION,
                    TrackColumns.NAME_ID + "=" + id,
                    null, null);
            cursor.moveToFirst();
            metadata = fromCursor(cursor);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return metadata;
    }

    public static Metadata firstTrackOfAlbum(int albumId, Context context) {
        Metadata metadata = new Metadata();
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(
                    TrackColumns.URI,
                    TrackColumns.PROJECTION,
                    TrackColumns.NAME_ALBUM_ID + "=" + albumId,
                    null, null);
            cursor.moveToFirst();

            metadata = fromCursor(cursor);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return metadata;
    }

    public static Metadata fromCursor(Cursor cursor) {
        Metadata metadata = new Metadata();
        metadata.setTrackId(cursor.getInt(TrackColumns.ID));
        metadata.setTrackTitle(cursor.getString(TrackColumns.TITLE));
        metadata.setDuration(cursor.getLong(TrackColumns.DURATION));
        metadata.setAlbumId(cursor.getInt(TrackColumns.ALBUM_ID));
        metadata.setArtistTitle(cursor.getString(TrackColumns.ARTIST));
        metadata.setAlbumTitle(cursor.getString(TrackColumns.ALBUM));
        metadata.setPath(cursor.getString(TrackColumns.PATH));
        metadata.setDateModified(cursor.getLong(TrackColumns.DATE_MODIFIED) * 1000);
        return metadata;
    }
}
