package com.edavtyan.materialplayer.app.resources;

import android.content.Context;
import android.util.TypedValue;

import com.edavtyan.materialplayer.app.R;

public final class AppColors {
    private AppColors() {}

    public static int getPrimary(Context context) {
        return resolveAttribute(context, R.attr.colorPrimary);
    }

    public static int getPrimaryDark(Context context) {
        return resolveAttribute(context, R.attr.colorPrimaryDark);
    }

    public static int getAccentColor(Context context) {
        return resolveAttribute(context, R.attr.colorAccent);
    }

    public static int getTextColorPrimary(Context context) {
        return resolveAttribute(context, R.attr.textColorPrimary);
    }


    private static int resolveAttribute(Context context, int attrId) {
        TypedValue attr = new TypedValue();
        context.getTheme().resolveAttribute(attrId, attr, true);
        return attr.data;
    }
}
