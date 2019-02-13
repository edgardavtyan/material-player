package com.edavtyan.materialplayer.utils;

public final class StringUtils {
	public static final String FILENAME_FILTER = "[?:\"*|/\\\\<>]";

	public static String encodeFilename(String filename) {
		return filename.replaceAll(FILENAME_FILTER, "");
	}
}
