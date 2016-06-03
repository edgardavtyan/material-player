package com.edavtyan.materialplayer.app.utils;

import java.util.concurrent.TimeUnit;

public final class DurationUtils {
	private DurationUtils() {}

	public static String toStringUntilHours(int millis) {
		long totalSeconds = TimeUnit.MILLISECONDS.toSeconds(millis);
		long seconds = totalSeconds % 60;
		long minutes = totalSeconds / 60;
		long hours = minutes / 60;

		if (hours == 0) {
			return String.format("%02d:%02d", minutes, seconds);
		} else {
			return String.format("%02d:%02d:%02d", hours, minutes, seconds);
		}
	}
}
