package com.edavtyan.materialplayer.app;

import android.app.Application;

import com.edavtyan.materialplayer.app.logging.CrashLogBuilder;
import com.edavtyan.materialplayer.app.logging.FileLogger;
import com.edavtyan.materialplayer.app.logging.Logger;
import com.edavtyan.materialplayer.app.utils.DataStorage;

import java.io.File;

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
        System.exit(1);
    }
}
