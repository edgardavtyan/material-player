package com.edavtyan.materialplayer.app.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

public class AlbumArtUtils {
    public static String getArtPathFromId(int id, Context context) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(
                    MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                    new String[] { MediaStore.Audio.Albums.ALBUM_ART },
                    MediaStore.Audio.Albums._ID + "=" + id,
                    null, null);

            cursor.moveToFirst();
            return cursor.getString(0);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
