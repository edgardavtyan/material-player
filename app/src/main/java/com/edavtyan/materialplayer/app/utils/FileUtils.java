package com.edavtyan.materialplayer.app.utils;

import java.io.File;

public final class FileUtils {
    private FileUtils() {}


    public static void delete(String filePath) {
        new File(filePath).delete();
    }
}
