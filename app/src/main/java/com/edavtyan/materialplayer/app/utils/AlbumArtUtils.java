package com.edavtyan.materialplayer.app.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.io.File;

public class AlbumArtUtils {
    public static File getArtFileFromId(int id, Context context) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(
                    MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                    new String[] { MediaStore.Audio.Albums.ALBUM_ART },
                    MediaStore.Audio.Albums._ID + "=" + id,
                    null, null);
            cursor.moveToFirst();

            String artPath = cursor.getString(0);
            return getArtFileFromPath(artPath);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static File getArtFileFromPath(String artPath) {
        File artFile = null;
        if (artPath != null) {
            artFile = new File(artPath);
        }

        return artFile;
    }
}
