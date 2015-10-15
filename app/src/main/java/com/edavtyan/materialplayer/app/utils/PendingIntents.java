package com.edavtyan.materialplayer.app.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public final class PendingIntents {
    private PendingIntents() {}

    public static PendingIntent getBroadcast(Context context, String action) {
        Intent intent = new Intent(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    public static PendingIntent getResumeActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(context, 0, intent, 0);
    }
}
