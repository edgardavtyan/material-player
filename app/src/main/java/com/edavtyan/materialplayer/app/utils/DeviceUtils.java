package com.edavtyan.materialplayer.app.utils;

import android.content.res.Configuration;
import android.content.res.Resources;

public final class DeviceUtils {
    private DeviceUtils() {}

    public static boolean isPortrait(Resources res) {
        return res.getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    public static boolean isLandscape(Resources res) {
        return res.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}
