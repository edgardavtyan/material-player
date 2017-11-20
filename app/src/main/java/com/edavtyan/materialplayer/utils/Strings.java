package com.edavtyan.materialplayer.utils;

public class Strings {
	public static String join(int[] array, String separator) {
		StringBuilder joinedArray = new StringBuilder();
		for (int anArray : array) joinedArray.append(anArray).append(separator);
		joinedArray = new StringBuilder(joinedArray.substring(0, joinedArray.length() - 1));
		return joinedArray.toString();
	}

	public static boolean nullOrEmpty(String str) {
		return str == null || str.equals("");
	}
}
