package com.edavtyan.materialplayer.app.music;

import android.content.Context;
import android.database.Cursor;

import com.edavtyan.materialplayer.app.music.columns.TrackColumns;

import lombok.Data;

@Data
public class Track {
    private int trackId;
    private int albumId;
    private long duration;
    private String trackTitle;
    private String artistTitle;
    private String albumTitle;
    private String path;
    private long dateModified;



    public static Track fromId(int id, Context context) {
        Track track = new Track();
        Cursor cursor = null;
        try {
            track.setTrackId(id);

            cursor = context.getContentResolver().query(
                    TrackColumns.URI,
                    TrackColumns.PROJECTION,
                    TrackColumns.NAME_ID + "=" + id,
                    null, null);
            cursor.moveToFirst();
            track = fromCursor(cursor);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return track;
    }

    public static Track firstTrackOfAlbum(int albumId, Context context) {
        Track track = new Track();
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(
                    TrackColumns.URI,
                    TrackColumns.PROJECTION,
                    TrackColumns.NAME_ALBUM_ID + "=" + albumId,
                    null, null);
            cursor.moveToFirst();

            track = fromCursor(cursor);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return track;
    }

    public static Track fromCursor(Cursor cursor) {
        Track track = new Track();
        track.setTrackId(cursor.getInt(TrackColumns.ID));
        track.setTrackTitle(cursor.getString(TrackColumns.TITLE));
        track.setDuration(cursor.getLong(TrackColumns.DURATION));
        track.setAlbumId(cursor.getInt(TrackColumns.ALBUM_ID));
        track.setArtistTitle(cursor.getString(TrackColumns.ARTIST));
        track.setAlbumTitle(cursor.getString(TrackColumns.ALBUM));
        track.setPath(cursor.getString(TrackColumns.PATH));
        track.setDateModified(cursor.getLong(TrackColumns.DATE_MODIFIED) * 1000);
        return track;
    }
}
