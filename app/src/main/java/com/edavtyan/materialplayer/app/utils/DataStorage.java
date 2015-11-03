package com.edavtyan.materialplayer.app.utils;

import android.os.Environment;

import java.io.File;

public final class DataStorage {
    private DataStorage() {}


    public static final File DATA_FOLDER = new File(
            Environment.getExternalStorageDirectory(), "MaterialPlayer");
    public static final File ART_FOLDER = new File(DATA_FOLDER, "artwork");


    static {
        ART_FOLDER.mkdirs();
    }
}
