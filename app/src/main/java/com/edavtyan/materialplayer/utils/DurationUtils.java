package com.edavtyan.materialplayer.utils;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class DurationUtils {
	private DurationUtils() {}

	public static String toStringUntilHours(long millis) {
		long totalSeconds = TimeUnit.MILLISECONDS.toSeconds(millis);
		long seconds = totalSeconds % 60;
		long minutes = (totalSeconds / 60) % 60;
		long hours = totalSeconds / 3600;

		if (hours == 0) {
			return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
		} else {
			return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
		}
	}
}
