package com.edavtyan.materialplayer.app.utils;

import android.os.Build;
import android.util.Log;

public final class CrashLogger {
    private static final String DIVIDER = "\n============\n";

    private StringBuilder logBuilder;


    public static CrashLogger from(Throwable throwable) {
        return new CrashLogger().addSection("STACK TRACE", Log.getStackTraceString(throwable));
    }


    private CrashLogger() {
        logBuilder = new StringBuilder();
        logBuilder.append("Build version: ").append(Build.VERSION.SDK_INT);
    }


    public CrashLogger addSection(String name, String body) {
        logBuilder
                .append("\n\n")
                .append(name)
                .append(DIVIDER)
                .append(body).append(DIVIDER);
        return this;
    }

    public String build() {
        return logBuilder.toString();
    }
}
