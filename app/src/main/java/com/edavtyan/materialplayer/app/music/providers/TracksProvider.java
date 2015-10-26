package com.edavtyan.materialplayer.app.music.providers;

import android.database.Cursor;

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
}
