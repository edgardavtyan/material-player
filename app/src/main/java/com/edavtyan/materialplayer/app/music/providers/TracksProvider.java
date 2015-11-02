package com.edavtyan.materialplayer.app.music.providers;

import android.content.Context;
import android.database.Cursor;

import com.edavtyan.materialplayer.app.music.Track;
import com.edavtyan.materialplayer.app.music.columns.TrackColumns;

import java.util.ArrayList;

public final class TracksProvider {
    private TracksProvider() {}


    public static ArrayList<Track> getAllTracks(Cursor cursor) {
        ArrayList<Track> tracks = new ArrayList<>();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            tracks.add(getTrackFromCursor(cursor));
        }

        return tracks;
    }

    public static ArrayList<Track> getAlbumTracks(int albumId, Context context) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(
                    TrackColumns.URI,
                    TrackColumns.PROJECTION,
                    TrackColumns.NAME_ALBUM_ID + "=" + albumId,
                    null,
                    TrackColumns.NAME_TRACK + " ASC");
            return getAllTracks(cursor);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static Track getTrackFromCursor(Cursor cursor) {
        Track track = new Track();
        track.setTrackId(cursor.getInt(TrackColumns.ID));
        track.setTrackTitle(cursor.getString(TrackColumns.TITLE));
        track.setDuration(cursor.getLong(TrackColumns.DURATION));
        track.setAlbumId(cursor.getInt(TrackColumns.ALBUM_ID));
        track.setArtistTitle(cursor.getString(TrackColumns.ARTIST));
        track.setAlbumTitle(cursor.getString(TrackColumns.ALBUM));
        track.setPath(cursor.getString(TrackColumns.PATH));
        track.setDateModified(cursor.getLong(TrackColumns.DATE_MODIFIED));
        return track;
    }
}
