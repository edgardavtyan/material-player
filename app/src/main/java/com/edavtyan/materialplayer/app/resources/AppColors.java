package com.edavtyan.materialplayer.app.resources;

import android.content.Context;
import android.util.TypedValue;

import com.edavtyan.materialplayer.app.R;

public final class AppColors {
    private AppColors() {}

    public static int getPrimary(Context context) {
        TypedValue color = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimary, color, true);
        return color.data;
    }

    public static int getPrimaryDark(Context context) {
        TypedValue color = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimaryDark, color, true);
        return color.data;
    }
}
