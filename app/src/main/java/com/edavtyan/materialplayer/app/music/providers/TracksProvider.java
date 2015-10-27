package com.edavtyan.materialplayer.app.music.providers;

import android.content.Context;
import android.database.Cursor;

import com.edavtyan.materialplayer.app.music.Metadata;
import com.edavtyan.materialplayer.app.music.columns.TrackColumns;

import java.util.ArrayList;

public final class TracksProvider {
    private TracksProvider() {}


    public static ArrayList<Integer> getAllIds(Cursor cursor) {
        ArrayList<Integer> trackIds = new ArrayList<>();
        cursor.moveToPosition(0);
        while (cursor.moveToNext()) {
            trackIds.add(cursor.getInt(TrackColumns.ID));
        }

        return trackIds;
    }

    public static ArrayList<Metadata> getAllTracks(Cursor cursor) {
        ArrayList<Metadata> tracks = new ArrayList<>();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            tracks.add(getTrackFromCursor(cursor));
        }

        return tracks;
    }

    public static ArrayList<Metadata> getAlbumTracks(int albumId, Context context) {
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

    public static Metadata getTrackFromCursor(Cursor cursor) {
        Metadata metadata = new Metadata();
        metadata.setTrackId(cursor.getInt(TrackColumns.ID));
        metadata.setTrackTitle(cursor.getString(TrackColumns.TITLE));
        metadata.setDuration(cursor.getLong(TrackColumns.DURATION));
        metadata.setAlbumId(cursor.getInt(TrackColumns.ALBUM_ID));
        metadata.setArtistTitle(cursor.getString(TrackColumns.ARTIST));
        metadata.setAlbumTitle(cursor.getString(TrackColumns.ALBUM));
        metadata.setPath(cursor.getString(TrackColumns.PATH));
        return metadata;
    }
}
