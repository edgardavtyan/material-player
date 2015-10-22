package com.edavtyan.materialplayer.app.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

public final class DataStorage {
    private DataStorage() {}


    private static final File DATA_FOLDER = new File(
            Environment.getExternalStorageDirectory(), "MaterialPlayer");
    private static final File ART_FOLDER = new File(DATA_FOLDER, "artwork");


    static {
        ART_FOLDER.mkdirs();
    }


    public static File readArt(int id) {
        return new File(ART_FOLDER, Integer.toString(id));
    }

    public static void saveArt(int id, byte[] buffer) {
        try {
            File artFile = readArt(id);
            FileOutputStream outputStream = new FileOutputStream(artFile);
            outputStream.write(buffer);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
