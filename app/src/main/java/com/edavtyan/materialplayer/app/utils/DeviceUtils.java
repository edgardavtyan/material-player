package com.edavtyan.materialplayer.app.utils;

import android.content.res.Resources;

public final class DeviceUtils {
    private DeviceUtils() {}

    public static int getStatusBarHeight(Resources res) {
        int result = 0;
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
