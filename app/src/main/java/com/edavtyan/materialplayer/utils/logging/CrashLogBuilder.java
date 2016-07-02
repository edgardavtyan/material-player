package com.edavtyan.materialplayer.utils.logging;

import android.os.Build;
import android.util.Log;

public final class CrashLogBuilder {
	private static final String DIVIDER = "\n============\n";

	private StringBuilder logBuilder;


	public static CrashLogBuilder from(Throwable throwable) {
		return new CrashLogBuilder().addSection("STACK TRACE", Log.getStackTraceString(throwable));
	}


	private CrashLogBuilder() {
		logBuilder = new StringBuilder();
		logBuilder.append("Build version: ").append(Build.VERSION.SDK_INT);
	}


	public CrashLogBuilder addSection(String name, String body) {
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
