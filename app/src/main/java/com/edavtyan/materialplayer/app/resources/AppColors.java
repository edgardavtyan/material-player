package com.edavtyan.materialplayer.app.resources;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.edavtyan.materialplayer.app.R;

public final class AppColors {
    private AppColors() {}

    public static int getPrimary(Context context) {
        return ContextCompat.getColor(context, R.color.primary);
    }

    public static int getPrimaryDark(Context context) {
        return ContextCompat.getColor(context, R.color.primary_dark);
    }
}
