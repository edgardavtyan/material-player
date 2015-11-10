package com.edavtyan.materialplayer.app;

import android.app.Application;
import android.util.Log;

import com.edavtyan.materialplayer.app.logging.FileLogger;
import com.edavtyan.materialplayer.app.logging.Logger;

public class App
        extends Application
        implements Thread.UncaughtExceptionHandler {
    private Logger logger;


    @Override
    public void onCreate() {
        super.onCreate();

        logger = new FileLogger();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        logger.log(throwable);
        Log.e("MaterialPlayer", "Uncaught exception", throwable);
        System.exit(1);
    }
}
