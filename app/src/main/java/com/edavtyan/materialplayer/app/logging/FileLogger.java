package com.edavtyan.materialplayer.app.logging;

import com.edavtyan.materialplayer.app.utils.DataStorage;

import java.io.File;

public class FileLogger implements Logger {
	@Override
	public void log(Throwable throwable) {
		String log = CrashLogBuilder.from(throwable).build();
		String logFileName = "log" + System.currentTimeMillis() + ".txt";
		File logFile = new File(DataStorage.DIR_LOG, logFileName);
		DataStorage.save(logFile, log);
	}
}
