package com.edavtyan.materialplayer.app.music.providers;

import android.content.Context;
import android.database.Cursor;

import com.edavtyan.materialplayer.app.music.data.Track;
import com.edavtyan.materialplayer.app.music.columns.TrackColumns;

import java.util.ArrayList;

public final class TracksProvider {
    private TracksProvider() {}


    public static ArrayList<Track> allFromCursor(Cursor cursor) {
        ArrayList<Track> tracks = new ArrayList<>();
        cursor.moveToPosition(-1);
        int queueIndex = 0;
        while (cursor.moveToNext()) {
            Track track = singleFromCursor(cursor);
            track.setQueueIndex(queueIndex);
            tracks.add(track);
            queueIndex++;
        }

        return tracks;
    }

    public static ArrayList<Track> allWithAlbumId(int albumId, Context context) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(
                    TrackColumns.URI,
                    TrackColumns.PROJECTION,
                    TrackColumns.NAME_ALBUM_ID + "=" + albumId,
                    null,
                    TrackColumns.NAME_TRACK + " ASC");
            return allFromCursor(cursor);
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    public static Track firstWithAlbumId(int albumId, Context context) {
        Track track = new Track();
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(
                    TrackColumns.URI,
                    TrackColumns.PROJECTION,
                    TrackColumns.NAME_ALBUM_ID + "=" + albumId,
                    null, null);
            cursor.moveToFirst();

            track = singleFromCursor(cursor);
        } finally {
            if (cursor != null) cursor.close();
        }

        return track;
    }

    public static Track withId(int id, Context context) {
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
            track = singleFromCursor(cursor);
        } finally {
            if (cursor != null) cursor.close();
        }

        return track;
    }

    public static Track singleFromCursor(Cursor cursor) {
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
