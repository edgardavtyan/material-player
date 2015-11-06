package com.edavtyan.materialplayer.app.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

public final class DataStorage {
    private DataStorage() {}


    public static final File DIR_BASE = Environment.getExternalStorageDirectory();
    public static final File DIR_DATA = new File(DIR_BASE, "MaterialPlayer");
    public static final File DIR_ART = new File(DIR_DATA, "artwork");
    public static final File DIR_LOG = new File(DIR_DATA, "logs");


    static {
        DIR_ART.mkdirs();
    }


    public static void save(File file, String contents) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(contents.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
