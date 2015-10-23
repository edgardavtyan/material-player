package com.edavtyan.materialplayer.app.music.providers;

import android.content.Context;
import android.database.Cursor;

import com.edavtyan.materialplayer.app.music.columns.ArtistColumns;

public final class ArtistsProvider {
    private ArtistsProvider() {}

    public static Cursor getAllArtists(Context context) {
        return context.getContentResolver().query(
                ArtistColumns.URI,
                ArtistColumns.PROJECTION,
                null, null,
                ArtistColumns.TITLE + " ASC");
    }
}
