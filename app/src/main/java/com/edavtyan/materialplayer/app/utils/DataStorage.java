package com.edavtyan.materialplayer.app.utils;

import android.os.Environment;

import java.io.File;

public final class DataStorage {
    private DataStorage() {}


    public static final File DIR_BASE = Environment.getExternalStorageDirectory();
    public static final File DIR_DATA = new File(DIR_BASE, "MaterialPlayer");
    public static final File DIR_ART = new File(DIR_DATA, "artwork");


    static {
        DIR_ART.mkdirs();
    }
}
