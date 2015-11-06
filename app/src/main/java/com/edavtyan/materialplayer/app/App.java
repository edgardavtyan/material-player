package com.edavtyan.materialplayer.app;

import android.app.Application;
import android.os.Environment;

import com.edavtyan.materialplayer.app.utils.CrashLogger;

import java.io.File;
import java.io.FileOutputStream;

public class App
        extends Application
        implements Thread.UncaughtExceptionHandler {
    @Override
    public void onCreate() {
        super.onCreate();

        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        String log = CrashLogger.from(throwable).build();
        String logFileName = "log" + System.currentTimeMillis() + ".txt";
        File logFile = new File(
                Environment.getExternalStorageDirectory(),
                "MaterialPlayer/logs" + logFileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(logFile);
            fileOutputStream.write(log.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.exit(1);
    }
}
